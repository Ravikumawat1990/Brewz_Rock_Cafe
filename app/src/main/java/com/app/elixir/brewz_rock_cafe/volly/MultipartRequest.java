package com.app.elixir.brewz_rock_cafe.volly;


import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.HttpHeaderParser;

import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 * Created by User on 20-07-2016.
 */
public class MultipartRequest extends Request<String> {
    private org.apache.http.entity.mime.MultipartEntity entity = new org.apache.http.entity.mime.MultipartEntity();

    private static final String FILE_PART_NAME = "image1";
    private static final String FILE_PART_NAME1 = "image2";
    private static final String FILE_PART_NAME2 = "image3";

    private final Response.Listener<String> mListener;
    private final String file, file1, file2;
    private final Map<String, String> params;
    File file_1, file_2, file_3;

    public MultipartRequest(String url, Response.Listener<String> listener, Response.ErrorListener errorListener, String file, String file1, String file2, Map<String, String> params) {
        super(Method.POST, url, errorListener);

        mListener = listener;
        this.file = file;
        this.file1 = file1;
        this.file2 = file2;
        this.params = params;
        buildMultipartEntity();
    }

    private void buildMultipartEntity() {

        if (file != null) {
            file_1 = new File(file);
        } else {
            file_1 = null;
        }


        if (file1 != null) {
            file_2 = new File(file1);
        } else {
            file_2 = null;
        }

        if (file2 != null) {
            file_3 = new File(file2);
        } else {
            file_3 = null;
        }



        /*try {

            if(file!=null){
                File file_1 = new File(file);

                entity.addPart(FILE_PART_NAME, new FileBody(file_1));
                entity.addPart(FILE_PART_NAME1, new StringBody(""));
                entity.addPart(FILE_PART_NAME2, new StringBody(""));

            }
            else if(file1!=null){
                File file_2 = new File(file1);
                entity.addPart(FILE_PART_NAME1, new FileBody(file_2));
            }
            else if(file2!=null){
                File file_3 = new File(file2);
                entity.addPart(FILE_PART_NAME2, new FileBody(file_3));
            }
            else if(file!=null && file1!=null&&file2!=null){
                File file_1 = new File(file);
                File file_2 = new File(file1);
                File file_3 = new File(file2);
                entity.addPart(FILE_PART_NAME,  new FileBody(file_1));
                entity.addPart(FILE_PART_NAME1, new FileBody(file_2));
                entity.addPart(FILE_PART_NAME2, new FileBody(file_3));
            }

        }catch (Exception e){

        }*/


        try {

           /* if (file == null) {

                entity.addPart(FILE_PART_NAME, new StringBody(""));
                entity.addPart(FILE_PART_NAME1, new FileBody(file_2));
              //  entity.addPart(FILE_PART_NAME2, new FileBody(file_3));

            }else if(file1 == null){

                entity.addPart(FILE_PART_NAME, new FileBody(file_1));
                entity.addPart(FILE_PART_NAME1, new StringBody(""));
              //  entity.addPart(FILE_PART_NAME2, new FileBody(file_3));

            }else if(file2 == null){

                entity.addPart(FILE_PART_NAME, new FileBody(file_1));
                entity.addPart(FILE_PART_NAME1, new FileBody(file_2));
                entity.addPart(FILE_PART_NAME2, new StringBody(""));

            }else if(file == null && file1 == null && file2 == null){

                entity.addPart(FILE_PART_NAME, new StringBody(""));
                entity.addPart(FILE_PART_NAME1, new StringBody(""));
                entity.addPart(FILE_PART_NAME2, new StringBody(""));

            } else if(file == null && file1 == null){

                entity.addPart(FILE_PART_NAME, new StringBody(""));
                entity.addPart(FILE_PART_NAME1, new StringBody(""));
             //   entity.addPart(FILE_PART_NAME2, new FileBody(file_3));

            }else if(file1 == null && file2 == null){

                entity.addPart(FILE_PART_NAME, new FileBody(file_1));
                entity.addPart(FILE_PART_NAME1, new StringBody(""));
                entity.addPart(FILE_PART_NAME2, new StringBody(""));

            }else if(file == null && file2 == null){

                entity.addPart(FILE_PART_NAME, new StringBody(""));
              //  entity.addPart(FILE_PART_NAME1, new FileBody(file_2));
                entity.addPart(FILE_PART_NAME2, new StringBody(""));

            }else {
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                //file.compress(Bitmap.CompressFormat.JPEG, 100, bos);
                byte[] data = bos.toByteArray();
                ByteArrayBody bab = new ByteArrayBody(data,
                        "image/jpeg", "record_img.png");
                entity.addPart(FILE_PART_NAME, new FileBody(file_1));
             //   entity.addPart(FILE_PART_NAME1, new FileBody(file_2));
              //  entity.addPart(FILE_PART_NAME2, new FileBody(file_3));
            }*/
            if (file == null) {
                //  entity.addPart(FILE_PART_NAME, new StringBody(""));
            } else if (file != null) {
                entity.addPart(FILE_PART_NAME, new FileBody(file_1));
            }

            if (file1 == null) {
                // entity.addPart(FILE_PART_NAME1, new StringBody(""));
            } else if (file1 != null) {
                entity.addPart(FILE_PART_NAME1, new FileBody(file_2));
            }


            if (file2 == null) {
                //entity.addPart(FILE_PART_NAME2, new StringBody(""));
            } else if (file2 != null) {
                entity.addPart(FILE_PART_NAME2, new FileBody(file_3));
            }

/*
            if (file != null && file1 != null && file2 != null) {
                entity.addPart(FILE_PART_NAME, new FileBody(file_1));
                entity.addPart(FILE_PART_NAME1, new FileBody(file_2));
                entity.addPart(FILE_PART_NAME2, new FileBody(file_3));
            }
            else if(file != null && file1 != null){
                entity.addPart(FILE_PART_NAME, new FileBody(file_1));
                entity.addPart(FILE_PART_NAME1, new FileBody(file_2));
                entity.addPart(FILE_PART_NAME2, new StringBody(""));
            }*/
            for (String key : params.keySet()) {
                entity.addPart(key, new StringBody(params.get(key)));
            }
        } catch (UnsupportedEncodingException e) {
            VolleyLog.e("UnsupportedEncodingException");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public String getBodyContentType() {
        return entity.getContentType().getValue();
    }

    @Override
    public byte[] getBody() throws AuthFailureError {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            entity.writeTo(bos);
        } catch (IOException e) {
            VolleyLog.e("IOException writing to ByteArrayOutputStream");
        }
        return bos.toByteArray();
    }

    @Override
    protected Response<String> parseNetworkResponse(NetworkResponse networkResponse) {
        String parsed;
        try {
            parsed = new String(networkResponse.data, HttpHeaderParser.parseCharset(networkResponse.headers));
        } catch (UnsupportedEncodingException e) {
            parsed = new String(networkResponse.data);
        }
        return Response.success(parsed, HttpHeaderParser.parseCacheHeaders(networkResponse));
    }

    @Override
    protected void deliverResponse(String response) {
        mListener.onResponse(response);
    }
}
