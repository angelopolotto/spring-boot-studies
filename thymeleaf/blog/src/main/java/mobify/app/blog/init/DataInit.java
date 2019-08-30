package mobify.app.blog.init;

//@Component
//public class DataInit implements ApplicationRunner {
//    private final PostRepository postRepository;
//
//    @Autowired
//    public DataInit(PostRepository postRepository) {
//        this.postRepository = postRepository;
//    }
//
//    @Override
//    public void run(ApplicationArguments args) throws Exception {
//        long count = postRepository.count();
//        if (count == 0) {
//            Post p = new Post(){{
//                setAutor("Teste nome");
//                setTexto("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Cras facilisis libero ut metus laoreet gravida. Nunc et condimentum diam. Duis dapibus odio nec fringilla bibendum. Nam et turpis condimentum, venenatis nibh eu, feugiat metus. Nulla dictum molestie sollicitudin. Aenean vel tortor aliquam, fermentum eros sed, gravida augue. Nulla volutpat posuere risus ultricies ullamcorper. Vivamus quis mauris id neque consequat laoreet. Aenean bibendum ex eget ullamcorper interdum. Vivamus ac mauris erat. Vestibulum ante ipsum, egestas rhoncus convallis ut, condimentum vitae lorem. Maecenas interdum lectus quis erat gravida blandit.\n" +
//                        "\n" +
//                        "Morbi tincidunt dictum mi, vel venenatis neque. Mauris vel elit risus. Pellentesque pharetra dolor non ante bibendum pretium. Nullam aliquet aliquet sodales. Curabitur ex turpis, aliquet vel blandit et, dignissim ut mauris. Pellentesque laoreet justo ante, sit amet elementum mauris facilisis vel. Quisque eu volutpat velit, ut feugiat nisl.\n" +
//                        "\n" +
//                        "Nulla condimentum dolor magna, non malesuada magna mollis nec. Maecenas vel molestie sapien, at aliquam elit. Vivamus malesuada nunc in elit luctus maximus. Morbi consequat mauris dolor, at sollicitudin sem gravida quis. Suspendisse facilisis ipsum ut tellus venenatis, eget consequat risus lobortis. Curabitur sit amet risus eu leo eleifend varius ac in mi. Nulla facilisi.\n" +
//                        "\n" +
//                        "Aenean quis interdum est. Cras mauris orci, ultrices vitae porttitor at, venenatis ut dui. Pellentesque iaculis eros nec diam placerat, sed pulvinar tellus malesuada. Vivamus a diam eget sapien feugiat sagittis non quis tellus. In accumsan lobortis leo, a consequat elit consequat sed. Suspendisse eleifend facilisis ligula, quis rutrum leo ultricies ac. In eu erat mollis, faucibus justo at, egestas magna. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Phasellus vel pulvinar nunc.");
//                setData(new Date());
//                setTitulo("Teste titulo");
//            }};
//
//            postRepository.saveAndFlush(p);
//        }
//    }
//}
