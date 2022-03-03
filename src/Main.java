import java.util.Scanner;

public class Main {

    private static final int NO_PARENT = -1;

    // Function Algoritma Dijkstra
    private static void dijkstra(int[][] adjacencyMatrix, int titikAwal, int titikAkhir)
    {
        int jumlah_vertex = adjacencyMatrix[0].length;

        // menyimpan jarak terpendek
        int[] shortestDistances = new int[jumlah_vertex];

        // true jika vertex termasuk jalur terpendek / udah di proses
        boolean[] added = new boolean[jumlah_vertex];

        // inisialisasi
        for (int vertexIndex = 0; vertexIndex < jumlah_vertex; vertexIndex++)
        {
            shortestDistances[vertexIndex] = Integer.MAX_VALUE;
            added[vertexIndex] = false;
        }

        // Jarak source ke dirinya sendiri 0
        shortestDistances[titikAwal] = 0;

        // menyimpan jalur yang terpendek
        int[] jalur = new int[jumlah_vertex];

        jalur[titikAwal] = NO_PARENT;

        // mencari jalur terpendek untuk setiap vertex
        for (int i = 1; i < jumlah_vertex; i++)
        {
            int VertexTerdekat = -1;
            int jarakTerpendek = Integer.MAX_VALUE;
            for (int vertexIndex = 0; vertexIndex < jumlah_vertex; vertexIndex++)
            {
                if (!added[vertexIndex] && shortestDistances[vertexIndex] < jarakTerpendek)
                {
                    VertexTerdekat = vertexIndex;
                    jarakTerpendek = shortestDistances[vertexIndex];
                }
            }

            added[VertexTerdekat] = true;

            for (int vertexIndex = 0; vertexIndex < jumlah_vertex; vertexIndex++)
            {
                int edgeDistance = adjacencyMatrix[VertexTerdekat][vertexIndex];

                if (edgeDistance > 0 && ((jarakTerpendek + edgeDistance) < shortestDistances[vertexIndex]))
                {
                    jalur[vertexIndex] = VertexTerdekat;
                    shortestDistances[vertexIndex] = jarakTerpendek + edgeDistance;
                }
            }
        }

        printSemuaJalur(titikAwal,shortestDistances,jalur);
        printHasil(titikAwal, titikAkhir, shortestDistances, jalur);
    }

    // print jarak dan membuat jalur
    private static void printSemuaJalur(int startVertex, int[] jarak, int[] jalur)
    {
        int jumlah_vertex = jarak.length;
        System.out.printf("\n\nJarak Terpendek dari Vertex %d ke semua Vertek adalah \n",startVertex);
        System.out.print("Vertex\t  Jarak\t    Jalur");

        for (int vertexIndex = 0;
             vertexIndex < jumlah_vertex;
             vertexIndex++)
        {
            if (vertexIndex != startVertex)
            {
                System.out.print("\n" + startVertex + " -> ");
                System.out.print(vertexIndex + " \t\t ");
                System.out.print(jarak[vertexIndex] + "\t\t");
                printJalur(vertexIndex, jalur);
            }
        }
    }
    private static void printHasil(int startVertex, int finishVertex, int[] jarak, int[] jalur)
    {
        int jumlah_vertex = jarak.length;
        System.out.printf("\nJarak Terpendek dari Vertex %d ke %d adalah \n",startVertex,finishVertex);
        System.out.print("Vertex\t  Jarak\t    Jalur");

        for (int vertexIndex = 0;
             vertexIndex < jumlah_vertex;
             vertexIndex++)
        {
            if (vertexIndex != startVertex )
            {
                System.out.print("\n" + startVertex + " -> ");
                System.out.print(finishVertex + " \t\t ");
                System.out.print(jarak[finishVertex] + "\t\t");
                printJalur(finishVertex, jalur);
                break;
            }
        }
    }

    // print jalur
    private static void printJalur(int currentVertex, int[] jalur)
    {
        if (currentVertex == NO_PARENT)
        {
            return;
        }
        printJalur(jalur[currentVertex], jalur);
        System.out.print(currentVertex + " ");
    }

    public static void main(String[] args)
    {
        System.out.println("Program Menentukan Jarak Terdekat");
        System.out.println("Ket :");
        System.out.println("0 = A\t1 = B\t2 = C\t3 = D\n4 = E\t5 = F\t6 = G\t7 = 5\n");

        Scanner userInput = new Scanner(System.in);
        System.out.print("Masukan Titik Awal : ");
        int masukan = userInput.nextInt();
        System.out.print("Masukan Titik Akhir : ");
        int masukan2 = userInput.nextInt();
        int[][] jarakMatrix = {
                {0, 4, 0, 0, 0, 0, 2, 6},
                {4, 0, 0, 1, 0, 0, 0, 0},
                {0, 0, 0, 0, 3, 0, 0, 5},
                {0, 1, 0, 0, 2, 0, 0, 0},
                {0, 0, 3, 2, 0, 2, 0, 0},
                {0, 0, 0, 0, 2, 0, 0, 0},
                {2, 0, 0, 0, 0, 0, 0, 0},
                {6, 0, 5, 0, 0, 0, 0, 0} };
        dijkstra(jarakMatrix, masukan, masukan2);

    }
}










// Copyrigth Avriansyah Bahtiar 2A TI PHB
