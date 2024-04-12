package org.example.util;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Scanner;
import org.json.JSONArray;
import org.json.JSONObject;

import static java.lang.System.getenv;

public class AnimalStatusQuery {
    private static final String API_URL = "https://openapi.gg.go.kr/ANIMALREGSTUS";
    private static final String API_KEY = "A";

    public void req() throws IOException, InterruptedException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("경기도에 가장 최근 등록된 반려동물 DB를 탐색합니다. \n");
        System.out.print("지역(ex.광주시): ");
        String sigunguNm = scanner.nextLine();
        System.out.print("종(ex.포메라니안): ");
        String speciesNm = scanner.nextLine();

        String response = sendRequest(sigunguNm, speciesNm);
        System.out.println(parseResponse(response, sigunguNm, speciesNm));
    }

    private static String sendRequest(String sigunguNm, String speciesNm) throws IOException, InterruptedException {
        String encodedSigunguNm = URLEncoder.encode(sigunguNm, StandardCharsets.UTF_8);
        String encodedSpeciesNm = URLEncoder.encode(speciesNm, StandardCharsets.UTF_8);

        String requestUrl = API_URL + "?KEY=" + API_KEY + "&Type=json" + "&pIndex=1&pSize=10"
                + "&SIGNGU_NM=" + encodedSigunguNm + "&SPECIES_NM=" + encodedSpeciesNm;
        //System.out.println(requestUrl);
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(requestUrl))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }

    private static String parseResponse(String response, String sigunguNm, String speciesNm) {
        try {
            JSONObject jsonObj = new JSONObject(response);
            JSONArray rows = jsonObj.getJSONArray("ANIMALREGSTUS").getJSONObject(1).getJSONArray("row");

            // 최신 연도 찾기
            int latestYear = rows.getJSONObject(0).getInt("BIRTH_YY");
            for (int i = 1; i < rows.length(); i++) {
                int year = rows.getJSONObject(i).getInt("BIRTH_YY");
                if (year > latestYear) {
                    latestYear = year;
                }
            }

            // 최신 연도에 해당하는 데이터 집계
            int totalCnt = 0;
            boolean isFierceDogPresent = false; // 맹견 여부 플래그
            StringBuilder resultBuilder = new StringBuilder();
            for (int i = 0; i < rows.length(); i++) {
                JSONObject row = rows.getJSONObject(i);
                if (row.getInt("BIRTH_YY") == latestYear) {
                    int count = row.getInt("CNT_AMNT");
                    totalCnt += count;
                    if (row.getString("FRC_DOG_YN").equals("Y")) {
                        isFierceDogPresent = true;
                    }
                    resultBuilder.append(String.format("경기도 %s의 %d년생 %s는 %s RFID로 등록된 %d마리가 존재합니다.\n",
                            sigunguNm, latestYear, speciesNm, row.getString("RFID_DIV"), count));
                }
            }

            // 총계와 맹견 여부 메시지 추가
            String frcDogStatus = isFierceDogPresent ? "맹견 입니다." : "맹견이 아닙니다.";
            resultBuilder.append(String.format("총 : %d 마리가 존재합니다. %s", totalCnt, frcDogStatus));

            return resultBuilder.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "검색 결과를 처리하는 중 오류가 발생했습니다.";
        }
    }


//    private static String parseResponse(String response, String sigunguNm, String speciesNm) {
//        JSONObject jsonObj = new JSONObject(response);
//        JSONArray animalRegstusArray = jsonObj.getJSONArray("ANIMALREGSTUS"); // ANIMALREGSTUS를 JSONArray로 처리
//        JSONObject rowsObj = animalRegstusArray.getJSONObject(1); // 'row' 객체가 있는 배열의 인덱스는 문서 구조에 따라 다를 수 있음
//        JSONArray rows = rowsObj.getJSONArray("row");
//
//        int totalCnt = 0;
//        for (int i = 0; i < rows.length(); i++) {
//            JSONObject row = rows.getJSONObject(i);
//            totalCnt += row.getInt("CNT_AMNT");
//        }
//
//        String frcDogStatus = "맹견 여부를 조회할 수 없습니다."; // 초기값 설정
//        if (rows.length() > 0) {
//            frcDogStatus = rows.getJSONObject(0).getString("FRC_DOG_YN").equals("N") ? "맹견이 아닙니다." : "맹견입니다.";
//        }
//        String result = sigunguNm + "에 등록된 " + speciesNm + "의 마릿수는 " + totalCnt + "마리 입니다. " + frcDogStatus;
//
//        return result;
//    }
}

