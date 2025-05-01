package week13;

/**
 * <h1>BAEKJOON 22860번 폴더 정리</h1>
 * <p>
 * JAVA11 : 메모리 83,000KB, 시간 696ms
 * </p>
 * 
 * @author KIM MINGYU jun3021303@gmail.com
 * @since 2025-04-29
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Baekjoon22860 {
    private static class Directory {
        Directory parent;
        Map<String, Directory> subDirectories;
        Set<String> files;
        int fileCount;

        Directory(Directory parent) {
            this.parent = parent;
            this.subDirectories = new HashMap<>();
            this.files = new HashSet<>();
            this.fileCount = 0;
        }

        void addSubDirectory(String name, boolean isFile) {
            if (isFile) {
                updateRoot(name);
            } else {
                Directory subDirectory = new Directory(this);
                subDirectories.put(name, subDirectory);
                allDirectories.put(name, subDirectory);
            }
        }

        void updateRoot(String fileName) {
            Directory current = this;
            while (current != null) {
                current.files.add(fileName);
                current.fileCount++;
                current = current.parent;
            }
        }
    }

    private static Map<String, Directory> allDirectories = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int N = input[0];
        int M = input[1];

        // 폴더 디렉토리, 파일 디렉토리 입력 분리
        List<String[]> folderEntries = new ArrayList<>(N);
        List<String[]> fileEntries = new ArrayList<>(M);
        for (int i = 0; i < N + M; i++) {
            String[] parts = br.readLine().split(" ");
            if (parts[2].equals("1")) {
                folderEntries.add(parts);
            } else {
                fileEntries.add(parts);
            }
        }

        // main 디렉토리 생성
        Directory root = new Directory(null);
        allDirectories.put("main", root);

        // 부모 디렉토리가 있으면 추가
        while (!folderEntries.isEmpty()) {
            boolean progressed = false;
            Iterator<String[]> it = folderEntries.iterator();
            while (it.hasNext()) {
                String[] p = it.next();
                Directory parentDirectory = allDirectories.get(p[0]);
                if (parentDirectory != null) {
                    parentDirectory.addSubDirectory(p[1], false);
                    it.remove();
                    progressed = true;
                }
            }
            if (!progressed) {
                break;
            }
        }

        // p[0] 부모 디렉토리
        // p[1] 파일 이름
        for (String[] p : fileEntries) {
            allDirectories.get(p[0]).addSubDirectory(p[1], true);
        }

        // 쿼리 입력
        StringBuilder sb = new StringBuilder();
        int Q = Integer.parseInt(br.readLine());
        while (Q-- > 0) {
            String[] query = br.readLine().split("/");
            // 조회할 최하위 디렉토리로 이동
            Directory finalDirectory = allDirectories.get(query[query.length - 1]);
            sb.append(finalDirectory.files.size()).append(" ").append(finalDirectory.fileCount).append("\n");
        }
        System.out.print(sb);
    }
}
