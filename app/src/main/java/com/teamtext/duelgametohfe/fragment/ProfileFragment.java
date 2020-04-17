package com.teamtext.duelgametohfe.fragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.loader.content.CursorLoader;
import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;
import com.teamtext.duelgametohfe.General;
import com.teamtext.duelgametohfe.Model.Update;
import com.teamtext.duelgametohfe.Model.profile.Profile;
import com.teamtext.duelgametohfe.Model.profile.ResultProfile;
import com.teamtext.duelgametohfe.R;
import com.teamtext.duelgametohfe.Req;
import com.teamtext.duelgametohfe.retrofit.ApiRetrofit;
import org.jetbrains.annotations.NotNull;
import java.io.File;
import java.security.Permission;
import java.util.HashMap;
import java.util.Map;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProfileFragment extends Fragment {

    private static final int GALLERY_REQUEST_CODE = 11;
    private Context context;
    private General general;
    private EditText name, email, bankName, shaba, cardNumber;
    private TextView coin, ticket, mobile;
    private Button send;
    private ImageView imgProfile;
    private ConstraintLayout swipe;
    private int ReadExternalRequestCode = 33;
    String path = null;
//    private SwipeRefreshLayout swipe;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
    }

    private void pickFromGallery() {
        //Create an Intent with action as ACTION_PICK
        Intent intent = new Intent(Intent.ACTION_PICK);
        // Sets the type as image/*. This ensures only components of type image are selected
        intent.setType("image/*");
        //We pass an extra array with the accepted mime types. This will ensure only components with these MIME types as targeted.
        String[] mimeTypes = {"image/jpeg", "image/png"};
        intent.putExtra(Intent.EXTRA_MIME_TYPES, mimeTypes);
        // Launching the Intent
        startActivityForResult(intent, GALLERY_REQUEST_CODE);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.profile, container, false);
        find(view);
        getFromServer();
        updateProfile();
        getFromGallery();
        return view;
    }

    private void getFromGallery() {
        imgProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                permissionMethod((Activity) context, Manifest.permission.READ_EXTERNAL_STORAGE,ReadExternalRequestCode);
                if (checkPermission(context))
                    pickFromGallery();
                else Toast.makeText(context, "اجازه دسترسی داده نشده", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void find(View view) {
        general = new General(context);
        mobile = view.findViewById(R.id.mobile);
        name = view.findViewById(R.id.name);
        coin = view.findViewById(R.id.coin);
        imgProfile = view.findViewById(R.id.imgProfile);
        ticket = view.findViewById(R.id.ticket);
        swipe = view.findViewById(R.id.main);
        send = view.findViewById(R.id.send);
        email = view.findViewById(R.id.email);
        bankName = view.findViewById(R.id.bankName);
        shaba = view.findViewById(R.id.shaba);
        cardNumber = view.findViewById(R.id.card_number);
    }

    private boolean checkPermission(final Context context) {
        int currentAPIVersion = Build.VERSION.SDK_INT;
        if (currentAPIVersion >= Build.VERSION_CODES.M) { // ابتدا کنترل می کند تا اندروید بالاتر M باشد
            if (ContextCompat.checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) { // اگر دسترسی خواندن مموری را نداشته باشد دستورات داخل شرط اجرا می شود.
                if (ActivityCompat.shouldShowRequestPermissionRationale((Activity) context, Manifest.permission.READ_EXTERNAL_STORAGE)) {
                    AlertDialog.Builder alertBuilder = new AlertDialog.Builder(context);//یک دیالوگ ایجاد می شود.
                    alertBuilder.setCancelable(true);// بصورت قابل کنسل شدنی
                    alertBuilder.setTitle("Permission necessary");// با عنوان نیازمند دسترسی
                    alertBuilder.setMessage("External storage permission is necessary"); // متن دسترسی
                    alertBuilder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() { // دکمه مثبت
                        @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
                        public void onClick(DialogInterface dialog, int which) {
                            ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, ReadExternalRequestCode); // نتیجه با کلید ReadExternalRequestCode فرستاده می شود.
                        }
                    });
                    AlertDialog alert = alertBuilder.create(); // دیالوگ را ایجاد میکند.
                    alert.show(); // دیالوگ را نمایش می دهد.

                } else {
                    ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, ReadExternalRequestCode); // نتیجه با کلید ReadExternalRequestCode فرستاده می شود.
                }
                return false;// دسترسی تایید نشده باشد جواب منفی داده می شود.
            } else {
                return true;// دسترسی تایید شده جواب مثبت می شود.
            }
        }
        else {
            return true;// دسترسی تایید شده جواب مثبت می شود.
        }
    }

    public void permissionMethod(Activity thisActivity,String permission,int MY_PERMISSIONS_REQUEST_READ_CONTACTS){
        if (ContextCompat.checkSelfPermission(thisActivity,permission) != PackageManager.PERMISSION_GRANTED) {
            // Permission is not granted
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(thisActivity,permission)) {
                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
            } else {
                // No explanation needed; request the permission
                ActivityCompat.requestPermissions(thisActivity, new String[]{permission}, MY_PERMISSIONS_REQUEST_READ_CONTACTS);
//                return true;
                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        } else {
//            return true;
            // Permission has already been granted
        }
    }

    private void getFromServer() {
        new Req(context, "getProfile", true) {
            @Override
            public void NotFind() {

            }

            @Override
            public void ResponseSuccess(String response) {
//                Log.e("RRRRRRRRRRRR",response);
                Gson gson = new Gson();
                Profile profile = gson.fromJson(response, Profile.class);
                if (profile.getStatus() == 200) {
                    ResultProfile resultProfile = gson.fromJson(profile.getResult(), ResultProfile.class);
                    coin.setText(String.valueOf(resultProfile.getCoin()));
                    ticket.setText(String.valueOf(resultProfile.getTicket()));
                    name.setText(String.valueOf(resultProfile.getName()));
                    mobile.setText(resultProfile.getMobile());
                    email.setText(resultProfile.getEmail());
                    general.setCoin(resultProfile.getCoin());
                    general.setTicket(resultProfile.getTicket());
                    general.setPicture(resultProfile.getPicture());
                    general.setBankName(resultProfile.getNameBank());
                    general.setCardNumber(resultProfile.getCard());
                    general.setShaba(resultProfile.getShaba());
                    general.setEmail(resultProfile.getEmail());
                    general.setNameInBank(resultProfile.getName());
                    bankName.setText(resultProfile.getNameBank());
                    cardNumber.setText(resultProfile.getCard());
                    shaba.setText(resultProfile.getShaba());
                    Log.e("PIC",resultProfile.getPicture()+"");
                    Picasso.get()
                            .load(Req.mainRoot+"khoda/"+resultProfile.getPicture())
                            .error(R.drawable.ic_person)
                            .placeholder(R.drawable.ic_person)
                            .into(imgProfile);
                }
            }

            @Override
            public void ResponseError(VolleyError error) {

            }

            @Override
            public Map<String, String> Parameters() {
                Map<String, String> map = new HashMap<>();
                map.put("token", general.getToken());
                map.put("type", "get");
                return map;
            }
        };
    }

    private String getRealPathFromURI(Uri contentUri) {
        String[] proj = {MediaStore.Images.Media.DATA};
        CursorLoader loader = new CursorLoader(context, contentUri, proj, null, null, null);
        Cursor cursor = loader.loadInBackground();
        assert cursor != null;
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        String result = cursor.getString(column_index);
        cursor.close();
        return result;
    }

    private void updateProfile() {
        send.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if (path==null)
                {
                    uploadToServerWithoutPic();
                }else {
                    uploadToServer(path);
                }
            }
        });
    }

    private void uploadToServer(String filePath) {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(Req.urlRoot).addConverterFactory(GsonConverterFactory.create()).build();
        ApiRetrofit uploadAPIs = retrofit.create(ApiRetrofit.class);
        File file = new File(filePath);
        RequestBody fileReqBody = RequestBody.create(MediaType.parse("image/*"), file);
        MultipartBody.Part part = MultipartBody.Part.createFormData("file", file.getName(), fileReqBody);
        MediaType text = MediaType.parse("text/plain");
        Call<Update> call = uploadAPIs.update(
                RequestBody.create(text,"update"),
                RequestBody.create(text,general.getToken()),
                RequestBody.create(text,name.getText().toString()),
                RequestBody.create(text,email.getText().toString()),
                RequestBody.create(text,bankName.getText().toString()),
                RequestBody.create(text,cardNumber.getText().toString()),
                RequestBody.create(text,shaba.getText().toString()), part);
        call.enqueue(new Callback<Update>() {
            @Override
            public void onResponse(@NotNull Call<Update> call, @NotNull Response<Update> response) {
                assert response.body() != null;
                Toast.makeText(context, response.body().getMessage(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(@NotNull Call<Update> call, @NotNull Throwable t) {
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void uploadToServerWithoutPic(){
        Retrofit retrofit = new Retrofit.Builder().baseUrl(Req.urlRoot).addConverterFactory(GsonConverterFactory.create()).build();
        ApiRetrofit uploadAPIs = retrofit.create(ApiRetrofit.class);
        Call<Update> call = uploadAPIs.up(
                "update",
                general.getToken(),
                name.getText().toString(),
                email.getText().toString(),
                bankName.getText().toString(),
                cardNumber.getText().toString(),
                shaba.getText().toString());
        call.enqueue(new Callback<Update>() {
            @Override
            public void onResponse(@NotNull Call<Update> call, @NotNull Response<Update> response) {
                assert response.body() != null;
                Toast.makeText(context, response.body().getMessage(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Update> call, Throwable t) {
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            switch (requestCode) {
                case GALLERY_REQUEST_CODE:
                    assert data != null;
                    Uri uri = data.getData();
                    path = getRealPathFromURI(uri);
                    Log.e("Pathhhhhh", path);
                    imgProfile.setImageURI(uri);
                    break;
            }
        }

        super.onActivityResult(requestCode, resultCode, data);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 33:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Log.e("A", "AA");
                    pickFromGallery();
                } else {
                    Toast.makeText(context, "دسترسی داده نشد!", Toast.LENGTH_LONG).show(); // در صورتی که جواب منفی گرفتیم پیام دسترسی داده نشد را نمایش می دهیم.
                }
                break;
        }
    }
}