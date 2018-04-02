
import java.util.LinkedList;
import java.util.Scanner;

public class functions {

    public static void main(String args[]) {
        //MAIN APENAS PARA TESTE COM MATRIZES TRADICIONAIS E NAO MANIPULANDO AS IMAGENS.
        Scanner sc = new Scanner(System.in);
        System.out.println("Digite um limiar: ");
        int m = sc.nextInt();
        int[][] matriz = new int[5][5];

        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz.length; j++) {
                matriz[i][j] = j;
            }

        }
        System.out.println("Matriz original:");
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz.length; j++) {
                System.out.printf("%d", matriz[i][j]);
            }
            System.out.println("");
        }
        FiltroMediana(7, matriz);

        int[][] ma = new int[12][12];

        int[][] ma2 = new int[14][14];
        // ma2 = AddBordas(ma, 12,12);

    }

    /*    
    public static Mat limiarizaçãoY(int m, Mat img) {
        Mat resultado = new Mat(img.rows(), img.cols(), CvType.CV_8UC3); //Matriz com resultado.
        Mat YIQ = new Mat(img.rows(), img.cols(), CvType.CV_8UC3); //Matriz YIQ.           
        YIQ = converterRGBYIQ(img); //Conversão RGB-YIQ.
    
        int tamanho = img.rows() * img.cols(); //Quantidade de elementos na matriz.
        int limiarUser = m; //Liminar (A princípio recebido pelo usuario).
        int limiarMedia = 0; //Limiar Medio.
        // Media das bandas;
        if (m == -1) { // Restrição caso nao seja escolhido pelo usuario.

            for (int i = 0; i < YIQ.rows(); i++) {
                for (int j = 0; j < YIQ.cols(); j++) {
                    double[] temp = YIQ.get(i, j);
                    limiarMedia += temp[0];                  

                }
            }
            limiarUser = (int) (limiarMedia / tamanho); //Novo limiar, caso não neja recebido pelo usuário.

        }
        // Limiarização
        for (int i = 0; i < img.rows(); i++) {
            for (int j = 0; j < img.cols(); j++) {
                double[] temp = YIQ.get(i, j);
                double[] Y = new double[3];

                if (temp[0] <= limiarUser) {
                    Y[0] = 0.299*255 + 0.587*255 + 0.114*255; // Y maximo.
                    Y[1] = temp[1];
                    Y[2] = temp[2];
                } else {
                    Y[0] = 0; //Y minimo.
                    Y[1] = temp[1];
                    Y[2] = temp[2];
                }
                resultado.put(i, j, Y);
            }
        }

        return resultado;
    }
     */
    /*
    public static Mat FiltroMediana(int Tamk, Mat img) {
        LinkedList<Double> KernelOrdenadoR = new LinkedList<Double>();
        LinkedList<Double> KernelOrdenadoG = new LinkedList<Double>();
        LinkedList<Double> KernelOrdenadoB = new LinkedList<Double>();
        int tamKernel = Tamk * Tamk; //Quantidade de elementos no Kernel;
        int nAddBordas = Tamk / 2; //Numero de adições de bordas variando com o kernel dado pelo usuario;
       // System.out.println("Numero de repetições para colocar borda: " + nAddBordas);

        Mat resultado = new Mat(img.rows(), img.cols(), CvType.CV_8UC3);// Resultado pos filtragem.
        Mat MBordas = img; // Matriz para manipulação com bordas.
        Mat kernel = new Mat(Tamk, Tamk, CvType.CV_8UC3); //Kernel.

        //Adicionando bordas.
        for (int a = 0; a < nAddBordas; a++) {
            MBordas = AddBordas(MBordas);
        }
        //Percorrendo os pixels da matriz original e realizando as medianass.
        for (int i = 0; i < img.rows(); i++) {
            for (int j = 0; j < img.cols(); j++) {

                System.out.println("i: " + i + "j: " + j);// Posições do pixel atual.
                // Pegando o kernel.
                int aux = 0, aux2 = 0; //linha e coluna do kernel.            
                int v = j, k = i; // linha e coluna da minha matriz com bordas.                
               // System.out.println("Começando com v: " + v + " e k: " + k);
                //Preenchendo o kernel.
                while (aux2 != Tamk) {
                    while (aux != Tamk) {
                    //    System.out.printf("Colocando no kernel[%d][%d] o meu MOriginal[%d][%d]\n", aux2, aux, k, v);
                        double[] temp = MBordas.get(k, v);
                        kernel.put(aux2, aux, temp);
                        aux++;
                        v++;
                    }
                    aux = 0;
                    v = j;
                    k++;
                    aux2++;
                }

                int Mediana = (int) (Math.round(tamKernel / 2.0)); // Kernel sendo sempre um quadrado impar.
               // System.out.println("Mediana: " + Mediana + " Tam Kernel: " + tamKernel);
                double[] menor = kernel.get(0, 0); //Assumindo RGB do meu Kernel[0][0] como parametro de comparação inicial;
                int aux3 = 0; // variavel auxiliar para evitar comparação com o kernel[0][0];               
                KernelOrdenadoR.add(menor[0]); // RED do kernel[0][0];
                KernelOrdenadoG.add(menor[1]); // GREEN do kernel[0][0];
                KernelOrdenadoB.add(menor[2]); // BLUE do kernel[0][0];

                //Mediana do pixel.
                double[] Final = new double[3];
                for (int a = 0; a < Tamk; a++) {
                    for (int b = 0; b < Tamk; b++) {
                        double[] temp = kernel.get(a, b);
                        //ORDENA RED.
                        for (int x = 0; x < KernelOrdenadoR.size(); x++) {
                            if ((temp[0] <= KernelOrdenadoR.get(x)) && (aux3 != 0)) {
                                //System.out.println("Adicionei "+kernel[a][b]+" na posicao "+x);
                                KernelOrdenadoR.add(x, temp[0]);
                                break;
                            }
                            if ((aux3 != 0) && (x == KernelOrdenadoR.size() - 1)) {
                                // System.out.println("add no final");
                                KernelOrdenadoR.add(temp[0]);
                                break;
                            }
                            aux3++;
                        }
                        //ORDENA GREEN.
                        aux3 = 0;
                        for (int x = 0; x < KernelOrdenadoG.size(); x++) {
                            if ((temp[1] <= KernelOrdenadoG.get(x)) && (aux3 != 0)) {
                                //System.out.println("Adicionei "+kernel[a][b]+" na posicao "+x);
                                KernelOrdenadoG.add(x, temp[1]);
                                break;
                            }
                            if ((aux3 != 0) && (x == KernelOrdenadoG.size() - 1)) {
                                // System.out.println("add no final");
                                KernelOrdenadoG.add(temp[1]);
                                break;
                            }
                            aux3++;
                        }
                        //ORDENA BLUE.
                        aux3 = 0;
                        for (int x = 0; x < KernelOrdenadoB.size(); x++) {
                            if ((temp[2] <= KernelOrdenadoB.get(x)) && (aux3 != 0)) {
                                //System.out.println("Adicionei "+kernel[a][b]+" na posicao "+x);
                                KernelOrdenadoB.add(x, temp[2]);
                                break;
                            }
                            if ((aux3 != 0) && (x == KernelOrdenadoB.size() - 1)) {
                                // System.out.println("add no final");
                                KernelOrdenadoB.add(temp[2]);
                                break;
                            }
                            aux3++;
                        }
                    }

                    Final[0] = KernelOrdenadoR.get(Mediana);//mediana R
                    Final[1] = KernelOrdenadoG.get(Mediana);//mediana G
                    Final[2] = KernelOrdenadoB.get(Mediana);//mediana B

                    resultado.put(i, j, Final); //insere no pixel atual a mediana de cada cor.

                }

            }
            return resultado;
        }
    */
        /*
    public static Mat FiltroMedia(int Tamk, Mat img) {
        int tamKernel = Tamk * Tamk; //Quantidade de elementos no Kernel;
        int nAddBordas = Tamk / 2; //Numero de adições de bordas variando com o kernel dado pelo usuario;
        System.out.println("Numero de repetições para colocar borda: " + nAddBordas);
        
        Mat resultado = new Mat(img.rows(), img.cols(), CvType.CV_8UC3);// Resultado pos filtragem.
        Mat MBordas = img; // Matriz para manipulação com bordas.
        Mat kernel = new Mat(Tamk, Tamk, CvType.CV_8UC3); //Kernel.

        //Adicionando bordas.
        for (int a = 0; a < nAddBordas; a++) {
            MBordas = AddBordas(MBordas);
        }
        //Percorrendo os pixels da matriz original e realizando as medias.
        for (int i = 0; i < img.rows(); i++) {
            for (int j = 0; j < img.cols(); j++) {

                System.out.println("i: " + i + "j: " + j);// Posições do pixel atual.
                // Pegando o kernel.
                int aux = 0, aux2 = 0; //linha e coluna do kernel.            
                int v = j, k = i; // linha e coluna da minha matriz com bordas.                
                System.out.println("Começando com v: " + v + " e k: " + k);
                //Preenchendo o kernel.
                while (aux2 != Tamk) {
                    while (aux != Tamk) {
                        System.out.printf("Colocando no kernel[%d][%d] o meu MOriginal[%d][%d]\n", aux2, aux, k, v);
                        double[] temp = MBordas.get(k, v);
                        kernel.put(aux2, aux, temp);
                        aux++;
                        v++;
                    }
                    aux = 0;
                    v = j;
                    k++;
                    aux2++;
                }
                //Media do pixel.
                double[]total = new double[3];
                for (int a = 0; a < Tamk; a++) {
                    for (int b = 0; b < Tamk; b++) {
                        double[] temp = kernel.get(a, b);
                        total[0] += temp[0];//R
                        total[1] += temp[1];//G
                        total[2] += temp[2];//B

                    }
                }
                total[0] = total[0]/tamKernel;//media R
                total[1] = total[1]/tamKernel;// media G
                total[2] = total[2]/tamKernel;// media B       
                resultado.put(i,j,total); //insere no pixel atual a media.
                
            }

        }    
        return resultado;
    }
         */
    public static void FiltroMedia(int Tamk, int matrix[][]) {
        int tamKernel = Tamk * Tamk;
        int nAddBordas = Tamk / 2;
        System.out.println("Numero de repetições para colocar borda: " + nAddBordas);

        int linha = 5, coluna = 5, linhaMO = 5, colunaMO = 5;
        int[][] MOriginal = matrix;
        int[][] kernel = new int[Tamk][Tamk];

        //Adicionando bordas.
        for (int a = 0; a < nAddBordas; a++) {
            MOriginal = AddBordas2(MOriginal, linhaMO, colunaMO);
            linhaMO += 2;
            colunaMO += 2;
        }
        //Percorrendo os pixels da matriz original e realizando as medias.
        for (int i = 0; i < linha; i++) {
            for (int j = 0; j < coluna; j++) {

                //kernel[Tamk - 2][Tamk - 2] = matrix[i][j];
                System.out.println("M OrignAL: ");
                for (int k = 0; k < linhaMO; k++) {
                    for (int v = 0; v < colunaMO; v++) {
                        System.out.printf("%d ", MOriginal[k][v]);
                    }
                    System.out.println("");
                }
                System.out.println("i: " + i + "j: " + j);

                // Pegando o kernel.
                int aux = 0, aux2 = 0; //linha e coluna do kernel.            
                int v = j, k = i; // linha e coluna da minha matriz com bordas.                
                System.out.println("Começando com v: " + v + " e k: " + k);
                //Preenchendo o kernel.
                while (aux2 != Tamk) {
                    while (aux != Tamk) {
                        System.out.printf("Colocando no kernel[%d][%d] o meu MOriginal[%d][%d]\n", aux2, aux, k, v);

                        kernel[aux2][aux] = MOriginal[k][v];
                        aux++;
                        v++;
                    }
                    aux = 0;
                    v = j;
                    k++;
                    aux2++;
                }
                //Kernel do pixel atual.
                System.out.println("Kernel: ");
                for (int a = 0; a < Tamk; a++) {
                    for (int b = 0; b < Tamk; b++) {
                        System.out.printf("%d ", kernel[a][b]);
                    }
                    System.out.println("");
                }

                //Media do pixel.
                int total = 0;
                for (int a = 0; a < Tamk; a++) {
                    for (int b = 0; b < Tamk; b++) {
                        total += kernel[a][b];
                    }
                    System.out.println("");
                }
                int media = (int) (total / tamKernel);
                System.out.println("Media = " + media);
                matrix[i][j] = media; //Coloca a media no pixel atual;

            }

        }
        //Matriz pós media.
        System.out.println("Matriz pos media: ");
        for (int a = 0; a < linha; a++) {
            for (int b = 0; b < coluna; b++) {
                System.out.printf("%d ", matrix[a][b]);
            }
            System.out.println("");
        }

    }

    public static void FiltroMediana(int Tamk, int matrix[][]) {
        LinkedList<Integer> KernelOrdenado = new LinkedList<Integer>();
        int tamKernel = Tamk * Tamk;
        int nAddBordas = Tamk / 2;
        System.out.println("Numero de repetições para colocar borda: " + nAddBordas);

        int linha = 5, coluna = 5, linhaMO = 5, colunaMO = 5;
        int[][] MOriginal = matrix;
        int[][] kernel = new int[Tamk][Tamk];

        //Adicionando bordas.
        for (int a = 0; a < nAddBordas; a++) {
            MOriginal = AddBordas2(MOriginal, linhaMO, colunaMO);
            linhaMO += 2;
            colunaMO += 2;
        }
        //Percorrendo os pixels da matriz original e realizando as medianass.
        for (int i = 0; i < linha; i++) {
            for (int j = 0; j < coluna; j++) {

                //kernel[Tamk - 2][Tamk - 2] = matrix[i][j];
                System.out.println("M OrignAL: ");
                for (int k = 0; k < linhaMO; k++) {
                    for (int v = 0; v < colunaMO; v++) {
                        System.out.printf("%d ", MOriginal[k][v]);
                    }
                    System.out.println("");
                }
                System.out.println("i: " + i + "j: " + j);

                // Pegando o kernel.
                int aux = 0, aux2 = 0; //linha e coluna do kernel.            
                int v = j, k = i; // linha e coluna da minha matriz com bordas.                
                System.out.println("Começando com v: " + v + " e k: " + k);
                //Preenchendo o kernel.
                while (aux2 != Tamk) {
                    while (aux != Tamk) {
                        System.out.printf("Colocando no kernel[%d][%d] o meu MOriginal[%d][%d]\n", aux2, aux, k, v);

                        kernel[aux2][aux] = MOriginal[k][v];
                        aux++;
                        v++;
                    }
                    aux = 0;
                    v = j;
                    k++;
                    aux2++;
                }
                //Kernel do pixel atual.
                System.out.println("Kernel: ");
                for (int a = 0; a < Tamk; a++) {
                    for (int b = 0; b < Tamk; b++) {
                        System.out.printf("%d ", kernel[a][b]);
                    }
                    System.out.println("");
                }

                //Mediana do pixel.
                int Mediana = (int) (Math.round(tamKernel / 2.0));
                System.out.println("Mediana: " + Mediana + " Tam Kernel: " + tamKernel);
                int menor = kernel[0][0];
                int aux3 = 0; // variavel auxiliar para evitar comparação com o kernel[0][0];               
                KernelOrdenado.add(menor);

                for (int a = 0; a < Tamk; a++) {
                    for (int b = 0; b < Tamk; b++) {

                        System.out.printf("Kernel[%d][%d]:\n", a, b);
                        //int indiceKernel = 0;
                        for (int x = 0; x < KernelOrdenado.size(); x++) {
                            if ((kernel[a][b] <= KernelOrdenado.get(x)) && (aux3 != 0)) {
                                System.out.println("Adicionei " + kernel[a][b] + " na posicao " + x);
                                KernelOrdenado.add(x, kernel[a][b]);
                                break;
                            }
                            if ((aux3 != 0) && (x == KernelOrdenado.size() - 1)) {
                                System.out.println("add no final");
                                KernelOrdenado.add(kernel[a][b]);
                                break;
                            }

                            aux3++;
                        }

                    }
                    System.out.println("");
                }
                System.out.println("KERNEL ORDENADO");
                for (int x = 0; x < KernelOrdenado.size(); x++) {
                    System.out.printf("%d", KernelOrdenado.get(x));

                }
                int mediana = KernelOrdenado.get(Mediana);
                System.out.println("Mediana = " + Mediana);
                matrix[i][j] = mediana; //Coloca a media no pixel atual;
                KernelOrdenado.clear();

                System.out.println("");
            }

        }
        //Matriz pós mediana.
        System.out.println("Matriz pos mediana: ");
        for (int a = 0; a < linha; a++) {
            for (int b = 0; b < coluna; b++) {
                System.out.printf("%d ", matrix[a][b]);
            }
            System.out.println("");
        }

    }

    /*
    public static Mat AddBordas(Mat img) {

        Mat MBordas = new Mat(img.rows() + 2, img.cols() + 2, CvType.CV_8UC3);
        for (int i = 0; i < MBordas.rows(); i++) {
            for (int j = 0; j < MBordas.cols(); j++) {
                // System.out.printf("i:%d, j: %d\n", i, j);
                if (i == 0) {//borda superior.
                    if (j == 0) {//diagonal esquerda superior.
                        double[] temp = img.get(i, j);
                        MBordas.put(i, j, temp);
                    } else if (j == img.cols() + 1) {//diagonal direita superior
                        double[] temp = img.get(i, img.cols() - 1);
                        MBordas.put(i, j, temp);
                    } else {
                        //demais bordas
                        double[] temp = img.get(i, j - 1);
                        MBordas.put(i, j, temp);

                    }
                } else if ((j == 0) && (i != 0) && (i != img.rows() + 1)) {//borda lateral esquerda
                    double[] temp = img.get(i - 1, j);
                    MBordas.put(i, j, temp);

                } else if ((j == img.cols() + 1) && (i != 0) && (i != img.rows() + 1)) {//borda lateral direita
                    double[] temp = img.get(i - 1, img.cols() - 1);
                    MBordas.put(i, j, temp);

                } else if (i == img.rows() + 1) {// borda inferior
                    if (j == 0) {// diagonal inferior esquerda
                        double[] temp = img.get(img.rows() - 1, j);
                        MBordas.put(i, j, temp);

                    } else if (j == img.cols() + 1) { // diagonal inferior direita
                        double[] temp = img.get(img.rows() - 1, img.cols() - 1);
                        MBordas.put(i, j, temp);

                    } else { // demas bordas inferiores
                        double[] temp = img.get(img.rows() - 1, j - 1);
                        MBordas.put(i, j, temp);

                    }

                } else {
                    // preenchendo o meio da matriz.
                    double[] temp = img.get(i - 1, j - 1);
                    MBordas.put(i, j, temp);
                }

            }
        }
        return MBordas;
    }
     */
    public static int[][] AddBordas2(int matrix[][], int linhas, int colunas) {
        int[][] MBordas = new int[linhas + 2][colunas + 2];

        for (int i = 0; i < linhas + 2; i++) {
            for (int j = 0; j < colunas + 2; j++) {
                //  System.out.printf("i:%d, j: %d\n", i, j);
                if (i == 0) {
                    if (j == 0) {
                        MBordas[i][j] = matrix[i][j];
                    } else if (j == colunas + 1) {
                        MBordas[i][j] = matrix[i][colunas - 1];
                    } else {
                        //  System.out.println("entrei");
                        MBordas[i][j] = matrix[i][j - 1];
                        //  System.out.println("normal");
                    }
                } else if ((j == 0) && (i != 0) && (i != linhas + 1)) {
                    MBordas[i][j] = matrix[i - 1][j];
                } else if ((j == colunas + 1) && (i != 0) && (i != linhas + 1)) {
                    MBordas[i][j] = matrix[i - 1][colunas - 1];
                } else if (i == linhas + 1) {
                    if (j == 0) {
                        MBordas[i][j] = matrix[linhas - 1][j];
                    } else if (j == colunas + 1) {
                        MBordas[i][j] = matrix[linhas - 1][colunas - 1];
                    } else {
                        MBordas[i][j] = matrix[linhas - 1][j - 1];
                    }

                } else {
                    MBordas[i][j] = matrix[i - 1][j - 1];
                }

            }
        }
        return MBordas;
    }

}
