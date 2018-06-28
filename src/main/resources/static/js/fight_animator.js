class Character {
    constructor(startPosX, startPosY, skeletonJSONPath, spriteAtlasJSONPath, spriteAtlasImagePath, armatureName, defaultAnimation) {
        this.speed = 3;
        this.startPosX = startPosX;
        this.startPosY = startPosY;
        this.goalPositionX = startPosX;
        this.skeletonPath = skeletonJSONPath;
        this.spriteAtlasPath = spriteAtlasJSONPath;
        this.spriteAtlasImagePath = spriteAtlasImagePath;
        this.armatureName = armatureName;
        this.defaultAnimation = defaultAnimation;
        this.ready = true;
        this.armatureDisplay = null;
    }
    moveTowardGoal(deltaTime) {
        console.log("Moving toward my goal");
        if (this.goalPositionX < this.armatureDisplay.x) {
            this.armatureDisplay.x  = Math.max(this.armatureDisplay.x - (this.speed * deltaTime), this.goalPositionX);
        } else {
            this.armatureDisplay.x  = Math.min(this.armatureDisplay.x + (this.speed * deltaTime), this.goalPositionX);
        }
    }
}

class FightAnimator extends PIXI.Container {
    constructor(width, height, playerCharacter, enemyCharacter) {
        super();
        this.fightEvents = Object.freeze({
            SUCCESFUL_ATTACK: Symbol("succ_atk"),
            UNSUCCESFUL_ATTACK: Symbol("unsucc_atk"),
            GET_REKT: Symbol("get_rekt")
        });

        this.succAttackStoryLine = [
            this.movePlayerToEnemy,
            function () {
                this.storyPartInProgress = true;
                this.player.armatureDisplay.animation.play("hit", 1);
            },
            function () {
                this.storyPartInProgress = true;
                this.enemy.armatureDisplay.animation.play("get_damaged", 1);
            },
            this.movePlayerToStart
        ];

        this.unsuccAttackStoryLine = [
            this.movePlayerNearEnemy,
            function () {
                this.storyPartInProgress = true;
                this.player.armatureDisplay.animation.play("hit", 1);
            },
            this.moveEnemyToCounterAttack,
            function () {
                this.storyPartInProgress = true;
                this.enemy.armatureDisplay.animation.play("hit", 1);
            },
            function () {
                this.storyPartInProgress = true;
                this.player.armatureDisplay.animation.play("get_damaged", 1);
            },
            this.movePlayerToStart,
            this.moveEnemyToStart
        ];

        this.currentEvent = null;
        this.currentCallback = null;
        this.storyPartInProgress = false;
        this.storyLineCounter = 0;
        this.player = playerCharacter;
        this.enemy = enemyCharacter;
        this.renderer = new PIXI.WebGLRenderer({width: width, height: height, transparent: true});
        this.resources = [];
        this.resources.push(
            this.player.skeletonPath,
            this.player.spriteAtlasPath,
            this.player.spriteAtlasImagePath,
            this.enemy.skeletonPath,
            this.enemy.spriteAtlasPath,
            this.enemy.spriteAtlasImagePath,
        );
        let domParent = document.getElementById("fight-scene");
        domParent.appendChild(this.renderer.view);
        this.loadResources();
    }

    loadResources() {
        let _this = this;
        for (let i = 0; i < this.resources.length; i++) {
            //Need to add it double, the first is the alias, the second is tha path
            PIXI.loader.add(_this.resources[i], _this.resources[i]);
        }
        PIXI.loader.once("complete", function (loader, resources) {
            _this.pixiResources = resources;
            _this.onStart();
            _this.startRenderTick(); // Make sure render after dragonBones update.
        });
        PIXI.loader.load();
    }

    onStart() {
        let factory = dragonBones.PixiFactory.factory;
        factory.parseDragonBonesData(this.pixiResources[this.player.skeletonPath].data);
        factory.parseDragonBonesData(this.pixiResources[this.enemy.skeletonPath].data);
        factory.parseTextureAtlasData(
            this.pixiResources[this.player.spriteAtlasPath].data,
            this.pixiResources[this.player.spriteAtlasImagePath].texture
        );
        factory.parseTextureAtlasData(
            this.pixiResources[this.enemy.spriteAtlasPath].data,
            this.pixiResources[this.enemy.spriteAtlasImagePath].texture
        );

        this.player.armatureDisplay = factory.buildArmatureDisplay(this.player.armatureName);
        this.enemy.armatureDisplay = factory.buildArmatureDisplay(this.enemy.armatureName);
        this.player.armatureDisplay.animation.play(this.player.defaultAnimation);
        this.enemy.armatureDisplay.animation.play(this.enemy.defaultAnimation);

        this.player.armatureDisplay.x = this.player.startPosX;
        this.player.armatureDisplay.y = this.player.startPosY;
        this.enemy.armatureDisplay.x = this.enemy.startPosX;
        this.enemy.armatureDisplay.y = this.enemy.startPosY;
        this.enemy.armatureDisplay.armature.flipX = true;

        this.player.armatureDisplay.on(dragonBones.EventObject.COMPLETE, this.animationEventHandler, this);
        this.enemy.armatureDisplay.on(dragonBones.EventObject.COMPLETE, this.animationEventHandler, this);

        this.addChild(this.player.armatureDisplay);
        this.addChild(this.enemy.armatureDisplay);
    }

    startRenderTick() {
        PIXI.ticker.shared.add(this.renderHandler, this);
    }

    renderHandler(deltaTime) {
        this.movementEventHandler(deltaTime);
        this.storyHandler();
        this.renderer.render(this);
    }

    animationEventHandler(event) {
        console.log(event.animationState.name, event.type, event.name);
        if (event.type === "complete"){
            this.storyPartInProgress = false;
            this.storyLineCounter++;
        }
    };

    movementEventHandler(deltaTime) {
        if (this.currentEvent === null) {
            return;
        }
        if (this.player.ready === false) {
            if (this.player.goalPositionX !== this.player.armatureDisplay.x) {
                this.player.moveTowardGoal(deltaTime);
            } else {
                this.player.ready = true;
                this.storyPartInProgress = false;
                this.storyLineCounter++;
            }
        }
        if (this.enemy.ready === false) {
            if (this.enemy.goalPositionX !== this.enemy.armatureDisplay.x) {
                this.enemy.moveTowardGoal(deltaTime);
            } else {
                this.enemy.ready = true;
                this.storyPartInProgress = false;
                this.storyLineCounter++;
            }
        }
    }

    storyHandler(){
        if (this.currentEvent === null || this.storyPartInProgress === true) {
            return;
        }
        switch (this.currentEvent) {
            case this.fightEvents.SUCCESFUL_ATTACK:
                if (this.storyLineCounter === this.succAttackStoryLine.length) {
                    this.eventCompleted();
                } else {
                    this.storyPartInProgress = true;
                    this.succAttackStoryLine[this.storyLineCounter].call(this);
                }
                break;
            case this.fightEvents.UNSUCCESFUL_ATTACK:
                if (this.storyLineCounter === this.unsuccAttackStoryLine.length) {
                    this.eventCompleted();
                } else {
                    this.storyPartInProgress = true;
                    this.unsuccAttackStoryLine[this.storyLineCounter].call(this);
                }
                break;
        }
    }

    /*
    update(deltaTime) {
        this.player.updatePosition(deltaTime);
        this.player.updateAnimation();
        this.enemy.updatePosition(deltaTime);
        this.enemy.updateAnimation();
    }*/

    playSuccefulAttack(callback) {
        this.currentEvent = this.fightEvents.SUCCESFUL_ATTACK;
        this.currentCallback = callback;
    }

    playUnsuccessfulAttack(callback) {
        this.currentEvent = this.fightEvents.UNSUCCESFUL_ATTACK;
        this.currentCallback = callback;
    }

    playGetRekt() {

    }

    movePlayerToEnemy() {
        this.player.armatureDisplay.animation.play("walk");
        this.player.ready = false;
        this.player.goalPositionX = 570;

    }

    movePlayerNearEnemy() {
        this.player.armatureDisplay.animation.play("walk");
        this.player.ready = false;
        this.player.goalPositionX = 480;

    }

    movePlayerToStart() {
        this.player.armatureDisplay.animation.play("walk");
        this.player.ready = false;
        this.player.goalPositionX = this.player.startPosX;
    }

    moveEnemyToCounterAttack() {
        this.enemy.armatureDisplay.animation.play("walk");
        this.enemy.ready = false;
        this.enemy.goalPositionX = 600;
    }

    moveEnemyToPlayer(){

    }

    moveEnemyToStart(){
        this.enemy.armatureDisplay.animation.play("walk");
        this.enemy.ready = false;
        this.enemy.goalPositionX = this.enemy.startPosX;
    }

    eventCompleted() {
        this.currentEvent = null;
        this.storyLineCounter = 0;
        this.storyPartInProgress = false;
        this.player.armatureDisplay.animation.play("idle");
        this.enemy.armatureDisplay.animation.play("idle");
        this.currentCallback();
        this.currentCallback = null;
    }
}

let ASSETPATH = "animation/";

let playerChar = new Character(
    100,
    250,
    ASSETPATH + "player_anim_ske.json",
    ASSETPATH + "player_anim_tex.json",
    ASSETPATH + "player_anim_tex.png",
    "player_armature",
    "idle"
);

let enemyChar = new Character(
    700,
    250,
    ASSETPATH + "skeleton_ske.json",
    ASSETPATH + "skeleton_tex.json",
    ASSETPATH + "skeleton_tex.png",
    "skeleton_armature",
    "idle"
);

let fight = new FightAnimator(800, 300, playerChar, enemyChar);