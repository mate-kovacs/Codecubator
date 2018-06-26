package com.codecool.poop.controller;

//@Controller
//public class Login {
//
//    @Autowired
//    private UserManager userManager;

//    public Login(UserManager userManager) {
//        this.userManager = userManager;
//    }

//    @GetMapping(value = "/")
//    public String loginScreen() {
//        return "login";
//    }

//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
//        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(request.getServletContext());
//        WebContext context = new WebContext(request, response, request.getServletContext());
//        engine.process("login.html", context, response.getWriter());
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
//        String name = request.getParameter("name");
//        String password = request.getParameter("password");
//        try {
//            User user = userManager.getUserByName(name);
//            if (BCrypt.checkpw(password, user.getPassword())) {
//                HttpSession session;
//                session = request.getSession();
//                Map<String, Object> userMap = new HashMap<>();
//                userMap.put("user_name", user.getUsername());
//                userMap.put("user_id", user.getId());
//                session.setAttribute("user", userMap);
//                System.out.println("User logged in!");
//            } else {
//                System.out.println("Passwords not matching");
//                response.setContentType("text/plain");
//                response.getWriter().print("Not matching");
//            }
//        } catch (NoResultException e) {
//            System.out.println("No such username");
//            response.setContentType("text/plain");
//            response.getWriter().print("Not matching");
//        }
//    }
//}


