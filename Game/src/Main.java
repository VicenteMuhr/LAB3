import java.util.*;
import java.io.*;

class Game{ //Clase Game según lo estipulado por el laboratorio.
    String Category;
    int Quality;
    String Name;
    int Price;

    Game(String Category, int Quality, String Name, int Price){
        this.Category = Category;
        this.Quality = Quality;
        this.Name = Name;
        this.Price = Price;
    }
    String getCategory(){return Category;}
    int getQuality(){return Quality;}
    String getName(){return Name;}
    int getPrice(){return Price;}

    public String toString(){ //Para que se vea más claro los datos
        return "Game{Category='" + Category + "', Quality=" + Quality +
                ", Name='" + Name + "', Price=" + Price + "}";
    }
}
class Dataset{ //Clase Dataset según lo estipulado por el laboratorio.
    ArrayList<Game> Data;
    String sortedByAttribute;

    Dataset(ArrayList<Game> Data){
        this.Data = Data;
        this.sortedByAttribute = null;
    }
    ArrayList<Game> getGamesByPrice(int price){
        ArrayList<Game> Results = new ArrayList<>();

        if("Price".equals(sortedByAttribute)){
            int index = BinarySearchByPrice(price);
            if(index != -1){
                int currentIndex = index;
                while(currentIndex >= 0 &&
                        Data.get(currentIndex).getPrice() == price){
                    Results.add(Data.get(currentIndex));
                    currentIndex--;
                }
                currentIndex = index + 1;
                while(currentIndex < Data.size() &&
                        Data.get(currentIndex).getPrice() == price){
                    Results.add(Data.get(currentIndex));
                    currentIndex++;
                }
            }
        } else {
            for(Game game : Data){
                if(game.getPrice() == price){
                    Results.add(game);
                }
            }
        }
        return Results;
    }
    int BinarySearchByPrice(int price) {
        int low = 0;
        int high = Data.size() - 1;
        int resultIndex = -1;

        while (low <= high) {
            int mid = (low + high) / 2;
            int midPrice = Data.get(mid).getPrice();

            if (midPrice == price) {
                resultIndex = mid;
                high = mid - 1;
            } else if (midPrice < price) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return resultIndex;
    }
    ArrayList<Game> getGamesByPriceRange(int lowerPrice, int higherPrice) {
        ArrayList<Game> Results = new ArrayList<>();

        if ("Price".equals(sortedByAttribute)) {
            int startIndex = findFirstIndex(lowerPrice);
            int endIndex = findLastIndex(higherPrice);

            if (startIndex != -1 && endIndex != -1 && startIndex <= endIndex) {
                for (int i = startIndex; i <= endIndex; i++) {
                    Results.add(Data.get(i));
                }
            }
        } else {
            for (Game game : Data) {
                if (game.getPrice() >= lowerPrice &&
                        game.getPrice() <= higherPrice) {
                    Results.add(game);
                }
            }
        }
        return Results;
    }
    int findFirstIndex(int target) {
        int low = 0;
        int high = Data.size() - 1;
        int firstIndex = -1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            int midPrice = Data.get(mid).getPrice();
            if (midPrice >= target) {
                firstIndex = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return firstIndex;
    }
    int findLastIndex(int target){
        int low = 0;
        int high = Data.size() - 1;
        int lastIndex = -1;

        while(low <= high){
            int mid = low + (high - low) / 2;
            int midPrice = Data.get(mid).getPrice();

            if(midPrice <= target){
                lastIndex = mid;
                low = mid + 1;
            }else{
                high = mid - 1;
            }
        }
        return lastIndex;
    }
    ArrayList<Game> getGamesByCategory(String Category){
        ArrayList<Game> results = new ArrayList<>();

        if("category".equals(sortedByAttribute)){
            int index = binarySearchByCategory(Category);
            if(index != -1) {
                int currentIndex = index;
                while(currentIndex >= 0 &&
                        Data.get(currentIndex).getCategory().equals(Category)){
                    results.add(Data.get(currentIndex));
                    currentIndex--;
                }
                currentIndex = index + 1;
                while(currentIndex < Data.size() &&
                        Data.get(currentIndex).getCategory().equals(Category)){
                    results.add(Data.get(currentIndex));
                    currentIndex++;
                }
            }
        }else{
            for(Game game : Data){
                if(game.getCategory().equals(Category)){
                    results.add(game);
                }
            }
        }
        return results;
    }
    private int binarySearchByCategory(String Category){
        int low = 0;
        int high = Data.size() - 1;
        int resultIndex = -1;

        while(low <= high){
            int mid = low + (high - low) / 2;
            String midCategory = Data.get(mid).getCategory();
            int comparison = midCategory.compareTo(Category);

            if(comparison == 0){
                resultIndex = mid;
                high = mid - 1;
            }else if(comparison < 0){
                low = mid + 1;
            }else{
                high = mid - 1;
            }
        }
        return resultIndex;
    }
    ArrayList<Game> getGamesByQuality(int Quality){
        ArrayList<Game> results = new ArrayList<>();
        if("quality".equals(sortedByAttribute)){
            int index = binarySearchByQuality(Quality);
            if(index != -1){
                int currentIndex = index;
                while(currentIndex >= 0 &&
                        Data.get(currentIndex).getQuality() == Quality){
                    results.add(Data.get(currentIndex));
                    currentIndex--;
                }
                currentIndex = index + 1;
                while(currentIndex < Data.size() &&
                        Data.get(currentIndex).getQuality() == Quality){
                    results.add(Data.get(currentIndex));
                    currentIndex++;
                }
            }
        } else {
            for(Game game : Data){
                if(game.getQuality() == Quality){
                    results.add(game);
                }
            }
        }
        return results;
    }
    private int binarySearchByQuality(int Quality) {
        int low = 0;
        int high = Data.size() - 1;
        int resultIndex = -1;

        while(low <= high){
            int mid = low + (high - low) / 2;
            int midQuality = Data.get(mid).getQuality();

            if(midQuality == Quality){
                resultIndex = mid;
                high = mid - 1;
            }else if(midQuality < Quality){
                low = mid + 1;
            }else{
                high = mid - 1;
            }
        }
        return resultIndex;
    }
    //CountingSort, este está diseñado para funcionar con el ejemplo pequeño.
    //NO FUNCIONARÁ CON ALGO TAN AMPLIO COMO EL EXPERIMENTAL, por como funciona el tipo de ordenamiento.
    private void countingSort(String attribute, int maxValue){
        int n = Data.size();
        if (n == 0) return;

        ArrayList<Game> output = new ArrayList<>(Collections.nCopies(n, null));
        int[] count = new int[maxValue + 1];
        Arrays.fill(count, 0);
        for(int i = 0; i < n; i++){
            int value;
            if ("price".equalsIgnoreCase(attribute)) {
                value = Data.get(i).getPrice();
            } else if ("quality".equalsIgnoreCase(attribute)) {
                value = Data.get(i).getQuality();
            } else {
                System.out.println("Counting Sort no aplicable para atributo: " + attribute);
                return;
            }
            if (value >= 0 && value <= maxValue) {
                count[value]++;
            } else {
                System.out.println("Advertencia: Valor fuera de rango para Counting Sort: " + value + " (Atributo: " + attribute + ")");
                return; // O manejar de otra forma
            }
        }
        for(int i = 1; i <= maxValue; i++){
            count[i] += count[i - 1];
        }
        for (int i = n - 1; i >= 0; i--) {
            int value;
            if ("price".equalsIgnoreCase(attribute)) {
                value = Data.get(i).getPrice();
            }else{
                value = Data.get(i).getQuality();
            }
            output.set(count[value] - 1, Data.get(i));
            count[value]--;
        }
        Data.clear();
        Data.addAll(output);
    }
    void sortByAlgorithm(String algorithm, String attribute){
        Comparator<Game> comparator = null;

        switch(attribute){
            case "price":
                comparator = Comparator.comparingInt(Game::getPrice);
                break;
            case "category":
                comparator = Comparator.comparing(Game::getCategory);
                break;
            case "quality":
                comparator = Comparator.comparingInt(Game::getQuality);
                break;
            default:
                comparator = Comparator.comparingInt(Game::getPrice);
                break;
        }
        switch(algorithm){
            case "bubbleSort":
                bubbleSort(comparator);
                break;
            case "insertionSort":
                insertionSort(comparator);
                break;
            case "selectionSort":
                selectionSort(comparator);
                break;
            case "mergeSort":
                mergeSort(Data, comparator);
                break;
            case "quickSort":
                quickSort(Data, comparator);
                break;
            default:
                Collections.sort(Data, comparator);
                break;
        }
        this.sortedByAttribute = attribute;
    }
    void bubbleSort(Comparator<Game> comparator){
        int n = Data.size();
        boolean swapped;
        for(int i = 0; i < n - 1; i++){
            swapped = false;
            for(int j = 0; j < n - i - 1; j++){
                if(comparator.compare(Data.get(j), Data.get(j + 1)) > 0){
                    Game temp = Data.get(j);
                    Data.set(j, Data.get(j + 1));
                    Data.set(j + 1, temp);
                    swapped = true;
                }
            }
            if(!swapped){
                break;
            }
        }
    }
    void insertionSort(Comparator<Game> comparator){
        int n = Data.size();
        for (int i = 1; i < n; ++i) {
            Game key = Data.get(i);
            int j = i - 1;

            while (j >= 0 && comparator.compare(Data.get(j), key) > 0) {
                Data.set(j + 1, Data.get(j));
                j = j - 1;
            }
            Data.set(j + 1, key);
        }
    }
    void selectionSort(Comparator<Game> comparator){
        int n = Data.size(); // Changed to Data
        for(int i = 0; i < n - 1; i++){
            int min_idx = i;
            for(int j = i + 1; j < n; j++){
                if (comparator.compare(Data.get(j), Data.get(min_idx)) < 0){
                    min_idx = j;
                }
            }
            Game temp = Data.get(i);
            Data.set(i, Data.get(min_idx));
            Data.set(min_idx, temp);
        }
    }
    private void mergeSort(ArrayList<Game> Data, Comparator<Game> comparator) {
        if(Data.size() > 1){
            int mid = Data.size() / 2;
            ArrayList<Game> left = new ArrayList<>(Data.subList(0, mid));
            ArrayList<Game> right = new ArrayList<>(Data.subList(mid, Data.size()));

            mergeSort(left, comparator);
            mergeSort(right, comparator);

            merge(Data, left, right, comparator);
        }
    }
    private void merge(ArrayList<Game> Data, ArrayList<Game> left,
                       ArrayList<Game> right, Comparator<Game> comparator){
        int i = 0, j = 0, k = 0;
        while(i < left.size() && j < right.size()){
            if(comparator.compare(left.get(i), right.get(j)) <= 0){
                Data.set(k++, left.get(i++));
            }else{
                Data.set(k++, right.get(j++));
            }
        }
        while(i < left.size()){
            Data.set(k++, left.get(i++));
        }
        while(j < right.size()){
            Data.set(k++, right.get(j++));
        }
    }
    private void quickSort(ArrayList<Game> Data, Comparator<Game> comparator){
        quickSortHelper(Data, 0, Data.size() - 1, comparator);
    }
    private void quickSortHelper(ArrayList<Game> Data, int low, int high,
                                 Comparator<Game> comparator) {
        if(low < high){
            int pi = partition(Data, low, high, comparator);
            quickSortHelper(Data, low, pi - 1, comparator);
            quickSortHelper(Data, pi + 1, high, comparator);
        }
    }
    private int partition(ArrayList<Game> Data, int low, int high,
                          Comparator<Game> comparator) {
        Game pivot = Data.get(high);
        int i =(low - 1);
        for(int j = low; j < high; j++){
            if(comparator.compare(Data.get(j), pivot) <= 0){
                i++;
                Game temp = Data.get(i);
                Data.set(i, Data.get(j));
                Data.set(j, temp);
            }
        }
        Game temp = Data.get(i + 1);
        Data.set(i + 1, Data.get(high));
        Data.set(high, temp);

        return i + 1;
    }
}
class GenerateData{ //Clase GenerateData según lo estipulado por el laboratorio.
    private static final String[] CATEGORIES = {"Action", "Adventure", "RPG",
            "Strategy", "Puzzle", "Sports", "Simulation", "Indie"};
    private static final String[] PREFIXES = {"Epic", "Mystic", "Dark",
            "Bright", "Futuristic", "Ancient", "Cyber"};
    private static final String[] SUFFIXES = {"Quest", "Chronicles",
            "Saga", "Odyssey", "Realm", "Frontier", "Legends"};
    public static final Random random = new Random();

    public static ArrayList<Game> generateRandomGames(int numberOfGames) {
        ArrayList<Game> games = new ArrayList<>(numberOfGames);

        for(int i = 0; i < numberOfGames; i++){
            String generatedCategory = CATEGORIES[random.nextInt(CATEGORIES.length)];
            int generatedQuality = random.nextInt(101);
            String generatedName = PREFIXES[random.nextInt(PREFIXES.length)] + " "
                    + SUFFIXES[random.nextInt(SUFFIXES.length)] + " "
                    + i;
            int generatedPrice = random.nextInt(100) + 1;
            games.add(new Game(generatedCategory, generatedQuality,
                    generatedName, generatedPrice));
        }
        return games;
    }
    //Esta sección es meramente para lectura fácil de los datos en csv.
    public static void saveGamesToCsv(ArrayList<Game> games, String filename){
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(filename))){
            bw.write("Category,Quality,Name,Price");
            bw.newLine();
            for(Game game : games){
                bw.write(game.getCategory() + "," +
                        game.getQuality() + ",\"" +
                        game.getName() + "\"," +
                        game.getPrice());
                bw.newLine();
            }
            System.out.println("Dataset guardado en: " + filename);
        } catch(IOException e){
            System.err.println("Error al guardar el dataset en CSV '" + filename + "': "
                    + e.getMessage());
        }
    }
}
public class Main{
    private static final long TIMEOUT_NS = 300L * 1_000_000_000L; //Para medir tiempos

    public static void main(String[] args){

    //En caso de querer usar lo experimental, esta sección se puede poner entre /* */
    //para evitar confusiones.
        Scanner scanner = new Scanner(System.in);
        ArrayList<Game> demoGames = GenerateData.generateRandomGames(20);
        String demoFilename = "games_demo_dataset.csv";
        GenerateData.saveGamesToCsv(demoGames, demoFilename);
        ArrayList<Game> loadedGames = loadGamesFromCsv(demoFilename);
        ArrayList<Game> orderedDemoGames = new ArrayList<>(loadedGames);
        Dataset demoDataset = new Dataset(orderedDemoGames);

        String[] algorithms ={"bubbleSort", "insertionSort", "selectionSort", "mergeSort",
                "quickSort", "countingSort", "Collections.sort"};
        String[] attributes ={"price", "category", "quality"};

        String chosenAlgorithm = "";
        String chosenAttribute = "";

        boolean validAlgorithm = false;
        while(!validAlgorithm){
            System.out.println("Introduce el algoritmo de ordenamiento:");
            System.out.print("Opciones: ");
            for(int i = 0; i < algorithms.length; i++){
                System.out.print(algorithms[i] + (i == algorithms.length - 1 ? "" : ", "));
            }
            System.out.print("\nTu elección: ");
            chosenAlgorithm = scanner.nextLine().trim();

            for(String algo : algorithms){
                if(algo.equalsIgnoreCase(chosenAlgorithm)){
                    chosenAlgorithm = algo;
                    validAlgorithm = true;
                    break;
                }
            }
            if(!validAlgorithm){
                System.out.println("Algoritmo no reconocido. Inténtalo de nuevo.");
            }
        }
        boolean validAttribute = false;
        while(!validAttribute){
            System.out.println("\nIntroduce el atributo para ordenar:");
            System.out.print("Opciones: ");
            for(int i = 0; i < attributes.length; i++){
                System.out.print(attributes[i] + (i == attributes.length - 1 ? "" : ", "));
            }
            System.out.print("\nTu elección: ");
            chosenAttribute = scanner.nextLine().trim();
            for(String attr : attributes){
                if(attr.equalsIgnoreCase(chosenAttribute)){
                    chosenAttribute = attr;
                    validAttribute = true;
                    break;
                }
            }
            if(!validAttribute){
                System.out.println("Atributo no reconocido. Inténtalo de nuevo.");
            }
        }
        demoDataset.sortByAlgorithm(chosenAlgorithm, chosenAttribute);
        System.out.println("\nDataset ordenado por " + chosenAttribute + " con " + chosenAlgorithm + ":");
        for(Game game : demoDataset.Data){
            System.out.println(game);
        }
        System.out.println("\n--- Realizando una búsqueda en el dataset ordenado ---");
        if(demoDataset.Data.isEmpty()){
            System.out.println("El dataset está vacío, no se puede realizar la búsqueda.");
        }else{
            Game randomGame = demoDataset.Data.get(GenerateData.random.nextInt(demoDataset.Data.size()));
            switch(chosenAttribute){
                case "price":
                    int searchPrice = randomGame.getPrice();
                    System.out.println("Buscando juegos con precio: " + searchPrice);
                    ArrayList<Game> foundByPrice = demoDataset.getGamesByPrice(searchPrice);
                    if (!foundByPrice.isEmpty()) {
                        foundByPrice.forEach(game -> System.out.println("  " + game.getName() +
                                " (Precio: " + game.getPrice() + ")"));
                    } else {
                        System.out.println("  No se encontraron juegos con ese precio.");
                    }
                    break;
                case "category":
                    String searchCategory = randomGame.getCategory();
                    System.out.println("Buscando juegos en la categoría: " + searchCategory);
                    ArrayList<Game> foundByCategory = demoDataset.getGamesByCategory(searchCategory);
                    if (!foundByCategory.isEmpty()) {
                        foundByCategory.forEach(game -> System.out.println("  " + game.getName() +
                                " (Categoría: " + game.getCategory() + ")"));
                    } else {
                        System.out.println("  No se encontraron juegos en esa categoría.");
                    }
                    break;
                case "quality":
                    int searchQuality = randomGame.getQuality();
                    System.out.println("Buscando juegos con calidad: " + searchQuality);
                    ArrayList<Game> foundByQuality = demoDataset.getGamesByQuality(searchQuality);
                    if (!foundByQuality.isEmpty()) {
                        foundByQuality.forEach(game -> System.out.println("  " + game.getName() +
                                " (Calidad: " + game.getQuality() + ")"));
                    } else {
                        System.out.println("  No se encontraron juegos con esa calidad.");
                    }
                    break;
                default:
                    System.out.println("No se puede realizar una búsqueda específica para el atributo '"
                            + chosenAttribute + "'.");
                    break;
            }
        }

        scanner.close();

    //Esta sección es únicamente para la experimentación con los tamaños y tiempos.
/*
        Scanner scanner = new Scanner(System.in);
        int[] datasetSizes = {100, 10000, 1000000};
        String[] algorithms = {"bubbleSort", "insertionSort", "selectionSort",
                "mergeSort", "quickSort", "Collections.sort", "countingSort"};
        String[] attributes = {"price", "category", "quality"};

        System.out.println("--- HERRAMIENTA DE BENCHMARKS DE ORDENAMIENTO INTERACTIVA ---");
        System.out.println("Los tiempos se medirán en milisegundos (ms). Timeout: "
                + (TIMEOUT_NS / 1_000_000_000L) + " segundos.");

        String chosenAlgorithm = "";
        String chosenAttribute = "";

        boolean validAlgorithm = false;
        while(!validAlgorithm){
            System.out.println("\nSelecciona un algoritmo de ordenamiento:");
            System.out.print("Opciones disponibles: ");
            for(int i = 0; i < algorithms.length; i++){
                System.out.print(algorithms[i] + (i == algorithms.length - 1 ? "" : ", "));
            }
            System.out.print("\nIntroduce el nombre exacto del algoritmo: ");
            chosenAlgorithm = scanner.nextLine().trim();

            for(String algo : algorithms){
                if(algo.equalsIgnoreCase(chosenAlgorithm)){
                    chosenAlgorithm = algo;
                    validAlgorithm = true;
                    break;
                }
            }
            if(!validAlgorithm){
                System.out.println("ADVERTENCIA: Algoritmo no reconocido. " +
                        "Se ejecutará 'Collections.sort' como predeterminado.");
            }
        }
        boolean validAttribute = false;
        while(!validAttribute){
            System.out.println("\nSelecciona un atributo para ordenar:");
            System.out.print("Opciones disponibles: ");
            for(int i = 0; i < attributes.length; i++){
                System.out.print(attributes[i] + (i == attributes.length - 1 ? "" : ", "));
            }
            System.out.print("\nIntroduce el nombre exacto del atributo: ");
            chosenAttribute = scanner.nextLine().trim();
            for(String attr : attributes){
                if(attr.equalsIgnoreCase(chosenAttribute)){
                    chosenAttribute = attr;
                    validAttribute = true;
                    break;
                }
            }
            if(!validAttribute){
                System.out.println("ADVERTENCIA: Atributo no reconocido. " +
                        "Se ordenará por 'price' como predeterminado.");
            }
        }

        System.out.println("\n--- Ejecutando Benchmark ---");
        System.out.println("Algoritmo seleccionado: " + chosenAlgorithm);
        System.out.println("Atributo de ordenamiento: " + chosenAttribute);
        System.out.println("----------------------------------------");
        System.out.printf("Tamaño Dataset", "Tiempo (ms)");
        System.out.println("----------------------------------------");

        for(int size : datasetSizes){
            ArrayList<Game> baseGames = loadGamesFromCsv("games_dataset_" + size + ".csv");
            if(baseGames.isEmpty()){
                System.err.println("Advertencia: No se pudo cargar el dataset para tamaño " + size
                        + ". Saltando este tamaño.");
                continue;
            }
            ArrayList<Game> gamesToProcess = new ArrayList<>(baseGames);
            Dataset dataset = new Dataset(gamesToProcess);
            long startTime = System.nanoTime();
            dataset.sortByAlgorithm(chosenAlgorithm, chosenAttribute);
            long endTime = System.nanoTime();
            long durationNs = (endTime - startTime);
            long durationMs = durationNs / 1_000_000;
            if (durationNs > TIMEOUT_NS){
                System.out.println(size + " | Más de " + (TIMEOUT_NS / 1_000_000_000L) + " segundos");
            }else{
                System.out.println(size + " | " + durationMs + " ms");
                System.out.println("Si 10^6 no se muestra, se debe al nivel de complejidad de " +
                        "tiempo BigO del algoritmo de ordenamiento.");
            }
        }
        System.out.println("\n--- Benchmark Finalizado ---");
        scanner.close();
 */
    }
    //Esta sección es meramente para lectura fácil de los datos en csv.
    private static ArrayList<Game> loadGamesFromCsv(String filename) {
        ArrayList<Game> games = new ArrayList<>();
        try(BufferedReader br = new BufferedReader(new FileReader(filename))){
            String line;
            br.readLine();
            while((line = br.readLine()) != null){
                int firstQuote = line.indexOf('"');
                int lastQuote = line.lastIndexOf('"');

                if(firstQuote == -1 || lastQuote == -1 || firstQuote == lastQuote){
                    String[] parts = line.split(",");
                    if(parts.length == 4){
                        try{
                            String category = parts[0];
                            int quality = Integer.parseInt(parts[1]);
                            String name = parts[2];
                            int price = Integer.parseInt(parts[3]);
                            games.add(new Game(category, quality, name, price));
                        }catch(NumberFormatException e){
                            System.err.println("Error parseando número en línea (simple split): " + line);
                        }
                    }else{
                        System.err.println("Línea malformada (simple split): " + line);
                    }
                }else{
                    try{
                        String category = line.substring(0, firstQuote - 1);
                        String name = line.substring(firstQuote + 1, lastQuote);

                        String preNamePart = line.substring(0, firstQuote - 1);
                        String[] preNameSplit = preNamePart.split(",");
                        int quality = Integer.parseInt(preNameSplit[1]);

                        String postNamePart = line.substring(lastQuote + 1);
                        String[] postNameSplit = postNamePart.split(",");
                        int price = Integer.parseInt(postNameSplit[1]);

                        games.add(new Game(category, quality, name, price));
                    }catch(NumberFormatException | ArrayIndexOutOfBoundsException e){
                        System.err.println("Error parseando línea CSV (con comillas): " + line
                                + " - " + e.getMessage());
                    }
                }
            }
        }catch (IOException e){
            System.err.println("Error al leer el archivo CSV '" + filename + "': " + e.getMessage());
        }
        return games;
    }
}
