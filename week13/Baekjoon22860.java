public class Main {
    static class Folder {
        String name;
        List<Folder> subFolders = new ArrayList<>();
        List<String> files = new ArrayList<>();

        Folder(String name) {
            this.name = name;
        }
    }

    static Map<String, Folder> folderMap = new HashMap<>();
    static int totalFileCount;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); 
        int M = Integer.parseInt(st.nextToken()); 
        Folder root = new Folder("main");
        folderMap.put("main", root);

        for (int i = 0; i < N + M; i++) {
            st = new StringTokenizer(br.readLine());
            String parentName = st.nextToken();
            String childName = st.nextToken();
            int isFolder = Integer.parseInt(st.nextToken());

            Folder parentFolder = folderMap.getOrDefault(parentName, new Folder(parentName));
            folderMap.putIfAbsent(parentName, parentFolder);

            if (isFolder == 1) {
                Folder childFolder = folderMap.getOrDefault(childName, new Folder(childName));
                folderMap.putIfAbsent(childName, childFolder);
                parentFolder.subFolders.add(childFolder);
            } else {
                parentFolder.files.add(childName);
            }
        }

        int Q = Integer.parseInt(br.readLine());
        for (int i = 0; i < Q; i++) {
            String path = br.readLine();
            String[] pathParts = path.split("/");
            String targetFolderName = pathParts[pathParts.length - 1];
            Folder targetFolder = folderMap.get(targetFolderName);

            Set<String> uniqueFiles = new HashSet<>();
            totalFileCount = 0;
            dfs(targetFolder, uniqueFiles);

            System.out.println(uniqueFiles.size() + " " + totalFileCount);
        }
    }

    static void dfs(Folder folder, Set<String> uniqueFiles) {
        for (String file : folder.files) {
            uniqueFiles.add(file);
            totalFileCount++;
        }
        for (Folder subFolder : folder.subFolders) {
            dfs(subFolder, uniqueFiles);
        }
    }
}
