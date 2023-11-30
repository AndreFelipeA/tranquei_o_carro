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
import dao.VerificaLocalizacaoDao;

public class CarsListScreen {

    public static boolean menu() throws IOException {
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
                return true;

            default:
                Carro selectedCar = Credenciais.getUsuarioLogado().getCarros().get(cmd - 1);
                CarsListScreen.carDisplay(selectedCar);
                break;
        }

        return false;
    }

    private static void carDisplay(Carro car) throws IOException {

        ControlaTrancamento controlaTrancamento = car.getControlaTrancamento();

        boolean exit = false;

        while (!exit) {

            String status = controlaTrancamento.getTrancado() ? "destrancar" : "trancar";

            // display car options
            CarsListScreen.printCar(car);
            System.out.println(
                    "Digite 1 para Excluir, 2 para " + status
                            + ", 3 para obter localização, 4 para alterar localização, 5 para obter Informaões e 6 para Alterar Informações ou -1 para Sair.");

            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            Integer cmd = 0;
            String choice = in.readLine();
            cmd = Integer.parseInt(choice);

            switch (cmd) {
                case 1:
                    CarsListScreen.deleteCar(car);
                    break;

                case 2:
                    controlaTrancamento.switchStatus();
                    break;
                case 3:
                    obterLocalizacao(car);
                    break;
                case 4:
                    alterarLocalizacao(car);
                    break;
                case 5:
                    obterInformaoes(car);
                    break;
                case 6:
                    alterarInformacoes(car);
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
        System.out.println("--------------------------");
        System.out.println(car.getModelo());
        System.out.println(car.getMarca());
        System.out.println("\nAno: " + car.getAno());
        System.out.println("ID: " + car.getIdCarro());
        System.out.println("--------------------------");
    }

    private static void listCars() {
        System.out.println("\nLista de carros");

        Usuario user = Credenciais.getUsuarioLogado();

        ArrayList<Carro> carros = user.getCarros();
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
        Carro car = new Carro(marca, modelo, Integer.parseInt(ano),null);
        CarroDao.adicionarCarro(marca, modelo, Integer.parseInt(ano), user.getId());
        int id = CarroDao.obterUltimoIdCarroInserido();
        car.setIdCarro(id);
        user.adicionarCarro(car);
    }

    


    private static void deleteCar(Carro car) {
        Usuario user = Credenciais.getUsuarioLogado();
        CarroDao.deletarCarro(car.getIdCarro());
        user.removerCarro(car);

    }

    private static void obterLocalizacao(Carro car)
    {
        VerificaLocalizacao vl = car.getVerificaLocalizacao();
        if(vl != null)
        {
            System.out.println(vl.obterLocalizacao());
        }

    }

    private static void alterarLocalizacao(Carro car) throws IOException
    {
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
    }

    private static void obterInformaoes(Carro car)
    {
        InformacaoDoCarro ic = car.getInformacaoDoCarro();
        if(ic != null)
        {
            System.out.println(ic.obterInfo());

        }
    }

    private static void alterarInformacoes(Carro car) throws IOException
    {
        InformacaoDoCarro ic = car.getInformacaoDoCarro();
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Digite a data de manutenção:");
        String dataManutencao = in.readLine().toString();
        System.out.println("Digite a Descricao:");
        String descricao = in.readLine().toString();
        System.out.println("Digite a Situacao:");
        String situacao = in.readLine().toString();
        ic.modificarInfo(dataManutencao, descricao, situacao);


    }
}
