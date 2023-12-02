package views;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import controllers.ControlaTrancamento;
import controllers.Credenciais;
import models.Carro;
import models.InformacaoDoCarro;
import models.Usuario;
import models.VerificaLocalizacao;
import dao.CarroDao;
import dao.InformacaoDoCarroDao;
import dao.VerificaLocalizacaoDao;

public class CarsListScreen {

    public static void display() throws IOException {
        Boolean exit = false;

        while (!exit) {
            CarsListScreen.listCars();

            // display car options
            System.out.println("Digite 0 para Adicionar carro, -1 para Sair ou selecione um carro.");

            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            Integer cmd = 0;
            String choice = in.readLine();
            cmd = Integer.parseInt(choice);

            switch (cmd) {
                case 0:
                    CarsListScreen.newCar();
                    break;

                case -1:
                    exit = true;
                    break;

                default:
                    Carro selectedCar = Credenciais.getUsuarioLogado().getCarros().get(cmd - 1);
                    System.out.print("\033[H\033[2J");
                    CarsListScreen.carDisplay(selectedCar);
                    break;
            }
        }
    }

    private static void carDisplay(Carro car) throws IOException {

        ControlaTrancamento controlaTrancamento = car.getControlaTrancamento();

        boolean exit = false;

        while (!exit) {

            String status = controlaTrancamento.getTrancado() ? "destrancar" : "trancar";

            // display car options
            System.out.println("Selecioanado: ");
            CarsListScreen.printCar(car);
            System.out.println(
                    "\nDigite 0 para Editar, 1 para Excluir, 2 para " + status
                            + ", 3 para obter localização, 4 para alterar localização, 5 para obter Informaões e 6 para Alterar Informações ou -1 para Sair.");

            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            Integer cmd = 0;
            String choice = in.readLine();
            cmd = Integer.parseInt(choice);

            switch (cmd) {
                case 0:
                    CarsListScreen.editCar(car);
                    break;

                case 1:
                    CarsListScreen.deleteCar(car);
                    exit = true;
                    break;

                case 2:
                    System.out.print("\033[H\033[2J");
                    controlaTrancamento.switchStatus();
                    break;
                case 3:
                    CarsListScreen.obterLocalizacao(car);
                    break;
                case 4:
                    CarsListScreen.alterarLocalizacao(car);
                    break;
                case 5:
                    System.out.print("\033[H\033[2J");
                    CarsListScreen.obterInformaoes(car);
                    break;
                case 6:
                    CarsListScreen.alterarInformacoes(car);
                    break;

                case -1:
                    exit = true;
                    break;

                default:
                    break;
            }
        }
    }

    private static void printCar(Carro car) {
        String status = car.getControlaTrancamento().getTrancado() ? "Trancado" : "Destrancado";
        System.out.println("--------------------------\n" + 
                            "Modelo: " + car.getModelo() +
                            "\nMarca: " + car.getMarca() +
                            "\nAno: " + car.getAno() +
                            "\nID: " + car.getIdCarro() +
                            "\nStatus: " + status +
                            "\n--------------------------"
                        );
    }

    private static void listCars() {
        
        Usuario user = Credenciais.getUsuarioLogado();
        ArrayList<Carro> carros = user.getCarros();

        if (carros.size() < 1)
        {
            System.out.println("Nenhum carro registrado para esse usuário.\n");
            return;
        }
        
        System.out.println("Lista de carros:\n");
        for (Integer i = 0; i < carros.size(); i++) {
            System.out.println((i + 1) + ":");
            CarsListScreen.printCar(carros.get(i));
            System.out.println("");
        }
    }

    private static void newCar() throws IOException {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Digite a marca do carro:");
        String marca = in.readLine().toString();
        System.out.println("Digite o modelo do carro:");
        String modelo = in.readLine().toString();
        System.out.println("Digite o ano do carro:");
        String ano = in.readLine().toString();

        Usuario user = Credenciais.getUsuarioLogado();
        Carro car = new Carro(marca, modelo, Integer.parseInt(ano), null);
        CarroDao.adicionarCarro(marca, modelo, Integer.parseInt(ano), user.getId());
        int id = CarroDao.obterUltimoIdCarroInserido();
        car.setIdCarro(id);
        user.adicionarCarro(car);

        System.out.print("\033[H\033[2J");
        System.out.println("Novo carro adicionado com sucesso.\n");
    }

    private static void deleteCar(Carro car) {
        Usuario user = Credenciais.getUsuarioLogado();
        CarroDao.deletarCarro(car.getIdCarro());
        user.removerCarro(car);

        System.out.print("\033[H\033[2J");
        System.out.println("Carro " + car.getMarca() + " excluído com sucesso.\n");
    }

    private static void obterLocalizacao(Carro car) {
        System.out.print("\033[H\033[2J");
        VerificaLocalizacao vl = car.getVerificaLocalizacao();
        if (vl != null) {
            System.out.println(vl.obterLocalizacao() + "\n");
        }

    }

    private static void alterarLocalizacao(Carro car) throws IOException {
        VerificaLocalizacao vl = car.getVerificaLocalizacao();
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Digite a latitude:");
        String x = in.readLine().toString();
        System.out.println("Digite a longitude:");
        String y = in.readLine().toString();
        Double latitude = Double.parseDouble(x);
        Double longitude = Double.parseDouble(y);
        vl.atualizarLocalizacao(latitude, longitude);
        VerificaLocalizacaoDao.salvarVerificaLocalizacao(latitude, longitude, car.getIdCarro());

        System.out.print("\033[H\033[2J");
        System.out.println("Localização alterada com sucesso.");
        System.out.println(vl.obterLocalizacao());
    }

    private static void obterInformaoes(Carro car) {
        InformacaoDoCarro ic = car.getInformacaoDoCarro();
        if (ic != null) {
            System.out.println(ic.obterInfo());
            System.out.println("");
        } else {
            System.out.println("Informações para esse veículo ainda não foram registradas.\n");
        }
    }

    private static void alterarInformacoes(Carro car) throws IOException {
        InformacaoDoCarro ic = new InformacaoDoCarro(null, null, null);
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Digite a data de manutenção:");
        String dataManutencao = in.readLine().toString();
        System.out.println("Digite a Descricao:");
        String descricao = in.readLine().toString();
        System.out.println("Digite a Situacao:");
        String situacao = in.readLine().toString();
        ic.modificarInfo(dataManutencao, descricao, situacao);
        car.setInformacaoDoCarro(ic);
        InformacaoDoCarroDao.salvarInformacaoDoCarro(situacao, descricao, dataManutencao, car.getIdCarro());

        System.out.print("\033[H\033[2J");
        System.out.println("Informações alteradas com sucesso.");
    }

    private static void editCar(Carro car) throws IOException {
        CarsListScreen.printCar(car);
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Digite a marca do carro:");
        String marca = in.readLine().toString();
        System.out.println("Digite o modelo do carro:");
        String modelo = in.readLine().toString();
        System.out.println("Digite o ano do carro:");
        Integer ano = Integer.parseInt(in.readLine().toString());

        car.setAno(ano);
        car.setMarca(marca);
        car.setModelo(modelo);
        CarroDao.atualizarCarro(car.getIdCarro(), marca, modelo, ano);
    }
}
