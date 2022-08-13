package com.common.reniors.controller.API;

import com.common.reniors.common.exception.ApiRequestException;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FileUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
//import org.apache.commons.io.FileUtils;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

@RestController
@RequestMapping("/vito")
@RequiredArgsConstructor
public class VitoController {
    @GetMapping("/videoId/{token}/{videoUrl}")
    public ResponseEntity<?> getVideoId(@PathVariable("token")String token,@PathVariable("videoUrl")String videoUrl){
        try {
            URL url = new URL("https://openapi.vito.ai/v1/transcribe");
            HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
            httpConn.setRequestMethod("POST");
            httpConn.setRequestProperty("accept", "application/json");
            httpConn.setRequestProperty("Authorization", "Bearer "+ token);
            httpConn.setRequestProperty("Content-Type", "multipart/form-data;boundary=authsample");
            httpConn.setDoOutput(true);

            URL vurl = new URL(videoUrl);
            File file = new File("video.mp4");

            FileUtils.copyURLToFile(vurl,file);

            DataOutputStream outputStream;
            outputStream = new DataOutputStream(httpConn.getOutputStream());

            outputStream.writeBytes("--authsample\r\n");
            outputStream.writeBytes("Content-Disposition: form-data; name=\"file\";filename=\"" + file.getName() +"\"\r\n");
            outputStream.writeBytes("Content-Type: " + URLConnection.guessContentTypeFromName(file.getName()) + "\r\n");
            outputStream.writeBytes("Content-Transfer-Encoding: binary" + "\r\n");
            outputStream.writeBytes("\r\n");

            FileInputStream in =new FileInputStream(file);

            byte[] buffer = new byte[(int)file.length()];
            int bytesRead = -1;
            while ((bytesRead = in.read(buffer)) != -1) {
                outputStream.write(buffer,0,bytesRead);
                outputStream.writeBytes("\r\n");
                outputStream.writeBytes("--authsample\r\n");
            }
            outputStream.writeBytes("\r\n");
            outputStream.writeBytes("--authsample\r\n");
            outputStream.writeBytes("Content-Disposition: form-data; name=\"config\"\r\n");
            outputStream.writeBytes("Content-Type: application/json\r\n");
            outputStream.writeBytes("\r\n");
            outputStream.writeBytes("{\n  \"diarization\": {\n");
            outputStream.writeBytes("	\"use_ars\": false,\n");
            outputStream.writeBytes("	\"use_verification\": false\n");
            outputStream.writeBytes("	},\n");
            outputStream.writeBytes("\"use_multi_channel\": false,\n");
            outputStream.writeBytes("\"use_itn\": false,\n");
            outputStream.writeBytes("\"use_disfluency_filter\": false,\n");
            outputStream.writeBytes("\"use_profanity_filter\": false,\n");
            outputStream.writeBytes("\"paragraph_splitter\": {\n");
            outputStream.writeBytes("	\"min\": 10,\n");
            outputStream.writeBytes("	\"max\": 50\n");
            outputStream.writeBytes("	}\n");
            outputStream.writeBytes("}");
            outputStream.writeBytes("\r\n");
            outputStream.writeBytes("--authsample\r\n");
            outputStream.flush();
            outputStream.close();

            InputStream responseStream = httpConn.getResponseCode() / 100 == 2
                    ? httpConn.getInputStream()
                    : httpConn.getErrorStream();
            Scanner s = new Scanner(responseStream).useDelimiter("\\A");
            String response = s.hasNext() ? s.next() : "";
            s.close();

            return ResponseEntity.status(HttpStatus.OK).body(response);
        }
        catch (Exception e){
            throw new ApiRequestException(e.getMessage());
        }
    }

    @GetMapping("/audioMsg/{token}/{videoId}")
    public ResponseEntity<?> getAudioMsg(@PathVariable("token")String token,@PathVariable("videoId") String videoId) throws Exception{
        try {
            URL url = new URL("https://openapi.vito.ai/v1/transcribe/"+videoId);
            HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
            httpConn.setRequestMethod("GET");
            httpConn.setRequestProperty("accept", "application/json");
            httpConn.setRequestProperty("Authorization", "Bearer "+token);

            InputStream responseStream = httpConn.getResponseCode() / 100 == 2
                    ? httpConn.getInputStream()
                    : httpConn.getErrorStream();
            Scanner s = new Scanner(responseStream).useDelimiter("\\A");
            String response = s.hasNext() ? s.next() : "";
            s.close();

            return ResponseEntity.status(HttpStatus.OK).body(response);
        }catch (Exception e){
            throw new ApiRequestException(e.getMessage());
        }
    }

}
