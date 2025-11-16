import dto.ProdutoDTO;
import entities.Produto;
import repositories.ProdutoRepository;
import services.ProdutoService;
import services.exceptions.DuplicatedResourceException;
import services.exceptions.ResourceNotFoundExeception;

import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class Program {
    private static ProdutoRepository repository = new ProdutoRepository();
    private static ProdutoService service = new ProdutoService(repository);

    public static void main(String[] args) throws ResourceNotFoundExeception {

//        ProdutoDTO dto = new ProdutoDTO(null, "p1", 100.00, 10);
//        ProdutoDTO dto2 = new ProdutoDTO(null, "p2", 100.00, 10);
//        ProdutoDTO dto3 = new ProdutoDTO(null, "p3", 100.00, 10);
//        ProdutoDTO dto4 = new ProdutoDTO(null, "p4", 100.00, 10);
//
//      dto = service.insert(dto);
//        service.insert(dto2);
//        service.insert(dto3);
//        service.insert(dto4);
//
//        List<ProdutoDTO> produtos = service.findAll();
//
//        produtos.forEach(System.out::println);
//        System.out.println();
//
//        System.out.println(service.findById(1));
//
//        ProdutoDTO dto5 = new ProdutoDTO(null, "p1", 100.00, 10);
//        service.insert(dto5);

        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();

        int option = 0;

        boolean sair = false;

        while(option != 5){
            sb.append("\n____ GERENCIADOR PRODUTOS ____\n");
            sb.append(
                    "1. Cadastrar produto\n" +
                    "2. Listar produtos\n" +
                    "3. Atualizar produtos\n" +
                    "4. Excluir produto\n" +
                    "5. Sair");

            System.out.println(sb);
            sb.setLength(0);
            System.out.print("Digite uma opção: ");

            option = sc.nextInt();
            sc.nextLine();

            menu(option);



            while(option == 1){
                System.out.println("Dados do novo produto");

                System.out.print("Nome: ");
                String nome = sc.nextLine();

                System.out.print("Valor: ");
                Double valor = sc.nextDouble();

                System.out.print("Quantidade: ");
                Integer qtd = sc.nextInt();


                if (inserirProduto(nome, valor, qtd)){
                    System.out.println("produto cadastrado com sucesso!\n");
                }

                System.out.print("Cadastra outro? digite 1 para cadastrar outro produto, 0 para sair: ");
                option = sc.nextInt();
                sc.nextLine();

            }

            if (option == 2){
                listarProdutos();
            }

            while (option == 3 ){
                System.out.print("Digite o id do Produto a ser atualizado: ");
                int id = sc.nextInt();

                try {
                    ProdutoDTO  dto = service.findById(id);

                    System.out.println("Produto: " + dto);

                    System.out.println("Selecione qual campo quer atualizar: ");
                    System.out.println("1. Nome");
                    System.out.println("2. Preço");
                    System.out.println("3. Quantidade");
                    System.out.println("4. Todos os campos");
                    System.out.print("Digite a opção: ");

                    int optionUpdate = sc.nextInt();
                    sc.nextLine();

                    switch (optionUpdate){
                        case 1 ->{
                            System.out.println("Nome atual: " + dto.getNome());
                            System.out.print("Novo nome: ");
                            String nome = sc.nextLine();

                            ProdutoDTO updateDto = new ProdutoDTO(dto.getId(), nome, dto.getPreco(), dto.getQuantidade());
                            atualizarProduto(updateDto);
                        }
                        case 2 ->{
                            System.out.println("Preço atual: " + dto.getPreco());
                            System.out.print("Novo preço: ");
                            Double preco = sc.nextDouble();

                            ProdutoDTO updateDto = new ProdutoDTO(dto.getId(), dto.getNome(), preco, dto.getQuantidade());
                            atualizarProduto(updateDto);
                        }

                        case 3 ->{
                            System.out.println("Quantidade atual: " + dto.getQuantidade());
                            System.out.print("Nova quantidade: ");
                            Integer qtd = sc.nextInt();

                            ProdutoDTO updateDto = new ProdutoDTO(dto.getId(), dto.getNome(), dto.getPreco(), qtd);
                            atualizarProduto(updateDto);
                        }
                        case 4 ->{
                            System.out.println("Produto atual: " + dto.getNome() + ", " + dto.getPreco() + ", " + dto.getQuantidade());
                            System.out.println("Novo nome: ");
                            String nome = sc.nextLine();

                            System.out.println("Novo nome: ");
                            Double preco = sc.nextDouble();

                            System.out.print("Nova quantidade: ");
                            Integer qtd = sc.nextInt();

                            ProdutoDTO updateDto = new ProdutoDTO(dto.getId(), nome, preco, qtd);
                            atualizarProduto(updateDto);
                        }
                    }

                    System.out.print("Atualizar outro? digite 3 para atualizar outro produto, 0 para sair: ");
                    option = sc.nextInt();
                    sc.nextLine();

                }catch (ResourceNotFoundExeception e){
                    System.out.println(e.getMessage());
                }


            }

            if(option == 4){
                listarProdutos();
                System.out.print("Digite o id do produto a ser deletado: ");
                int id = sc.nextInt();

                if (deletarProduto(id)){
                    System.out.println("produto exlcluido");
                    listarProdutos();
                }
            }


        }

        sc.close();

    }

    static void menu(int option){
        switch (option) {
            case 1 -> System.out.println("____ Inserir Produto ____");
            case 2 -> System.out.println("____ Listagem de Produtos ____");
            case 3 -> System.out.println("____ Atualizar Produto Cadastrado ____");
            case 4 -> System.out.println( "____ Excluir Produto ____");
            case 5 -> System.out.println("saindo...");
            default -> System.out.println("Opção inválida");
        }
    }

    static void listarProdutos(){
        List<ProdutoDTO> produtos = service.findAll();
        produtos.forEach(System.out::println);
    }

    static boolean inserirProduto(String nome, Double valor, Integer quantidade){
        try {
            service.insert(new ProdutoDTO(null, nome, valor, quantidade));
            return true;
        }catch (DuplicatedResourceException e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    static void atualizarProduto(ProdutoDTO dto){
        try{
            service.update(dto);
        }catch (ResourceNotFoundExeception e){
            System.out.println(e.getMessage());
        }
    }

    static boolean deletarProduto(int id){
        try {
            service.delete(id);
            return true;
        }catch (ResourceNotFoundExeception e){
            System.out.println(e.getMessage());
            return false;
        }
    }
}
