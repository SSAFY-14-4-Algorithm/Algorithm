import java.io.*;
import java.util.*;

/* 폴더정리(small)
 * 828212kb, 560ms
 */

public class Baekjoon22860 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        
        //각 폴더들의 정보를 저장해놓을 폴더맵
        HashMap<String, Folder> fMap = new HashMap<>();

        for (int i = 0; i < N + M; i++) {
            st = new StringTokenizer(br.readLine());
            
            String P = st.nextToken();
            String F = st.nextToken();
            String C = st.nextToken();
            
            //없는 폴더라면 생성
            fMap.putIfAbsent(P, new Folder());
            
            //폴더 가져오기
            Folder parentFolder = fMap.get(P);
            
            
            if (C.equals("1")) { //폴더라면
            	//이미 만들어진 폴더면 그 폴더 가져오고, 없으면 새로 만들기
                Folder folder = fMap.getOrDefault(F, new Folder());
                //상위 폴더에 폴더 추가
                parentFolder.addFolder(F, folder);
                //폴더맵에 추가해주기
                fMap.put(F, folder);
            } else { //파일이라면
            	//상위 폴더에 파일 추가
                parentFolder.addFile(F);
            }
        }

        int Q = Integer.parseInt(br.readLine());

        for (int i = 0; i < Q; i++) {
            String[] q = br.readLine().split("/");
            String s = q[q.length-1];
            Folder folder = fMap.get(s);
            File ans = folder.getFile();
            sb.append(ans.files.size()).append(" ").append(ans.fileCount).append("\n");
        }
        System.out.print(sb);
    }

    static class Folder {

        HashMap<String, Folder> folders; //폴더를 모아놓은 Map
        HashSet<String> files; //파일을 모아놓은 Set
        File cntFile; //해당 폴더 아래의 파일 종류 및 개수

        public Folder() {
            folders = new HashMap<>();
            files = new HashSet<>();
        }
        
        //파일 추가
        public void addFile(String file) {
            files.add(file);
        }

        //폴더 추가
        public void addFolder(String name, Folder folder) {
            folders.put(name, folder);
        }
        
        //파일 정보 가져오기
        public File getFile() {
            if (cntFile != null) {
                return cntFile; //이미 파일 정보가 있으면 반환 (메모이제이션)
            }
            
            //해당 폴더의 파일을 가진 파일들을 넣은 셋 생성
            HashSet<String> tempFiles = new HashSet<>(files);
            
            //해당 폴더의 파일 개수 세기
            int fileCount = files.size();
            
            //해당 폴더의 폴더들의 파일 정보 가져오기
            for (Folder folder : folders.values()) {
            	//각 폴더들의 파일 정보 가져오기
                File file = folder.getFile();
                //가져온 파일정보의 Set을 현재 셋에 추가
                tempFiles.addAll(file.files);
                //가져온 파일정보의 파일 갯수를 더해주기
                fileCount += file.fileCount;
            }
            
            //파일정보 저장해주고 return
            return cntFile = new File(tempFiles, fileCount);
        }
    }

    static class File {
    	
    	//파일 중복을 없애주고 파일들을 모아놓은 셋
        HashSet<String> files;
        
        //파일 갯수
        int fileCount;

        public File(HashSet<String> files, int fileCount) {
            this.files = files;
            this.fileCount = fileCount;
        }
    }
}