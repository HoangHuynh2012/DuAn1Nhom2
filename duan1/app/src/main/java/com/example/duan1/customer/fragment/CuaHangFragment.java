package com.example.duan1.customer.fragment;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.annotation.DrawableRes;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import com.example.duan1.customer.DiaChiQuan;
import com.example.duan1.R;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class CuaHangFragment extends Fragment {
    MapView mMapView;
    private GoogleMap googleMap;
    Spinner spn;
    ArrayAdapter<String> adapter;
    ArrayList<String> list = new ArrayList<>();
    int PERMISSION_ALL = 1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cua_hang, container, false);
        // Cấp quyền
        String[] PERMISSIONS = {
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.READ_CALL_LOG,
        };
        if (!hasPermissions(getContext(), PERMISSIONS)) {

            ActivityCompat.requestPermissions(getActivity(), PERMISSIONS, PERMISSION_ALL);

        }
        spn = view.findViewById(R.id.spn);
        list.clear();
        list.add("Quận 1");
        list.add("Quận 2");
        list.add("Quận 3");
        list.add("Quận 4");
        list.add("Quận 5");
        list.add("Quận 6");
        list.add("Quận 7");
        list.add("Quận 9");
        list.add("Quận 10");
        list.add("Quận 11");
        list.add("Quận 12");
        list.add("Quận Bình Tân");
        list.add("Quận Bình Thạnh");
        list.add("Quận Gò Vấp");
        list.add("Quận Phú Nhuận");
        list.add("Quận Tân Bình");
        list.add("Quận Tân Phú");
        list.add("Quận Thủ Đức");
        list.add("Huyện Bình Chánh");
        list.add("Huyện Hóc Môn");
        adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, list);
        spn.setAdapter(adapter);
        mMapView = (MapView) view.findViewById(R.id.mapView);
        mMapView.onCreate(savedInstanceState);
        mMapView.onResume();
        try {
            MapsInitializer.initialize(getActivity().getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }

        mMapView.getMapAsync(mMap -> {
            googleMap = mMap;
            googleMap.resetMinMaxZoomPreference();
//             Hiển thị nút vị trí của tôi
            if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return ;
            }
            googleMap.setMyLocationEnabled(true);
            // Đánh dấu một điểm trên bản đồ
            List<LatLng> locations = new ArrayList<>();
            locations.add(new LatLng(10.768345,106.698828)); // Quận 1
            locations.add(new LatLng(10.803691,106.733104)); // Quận 2
            locations.add(new LatLng(10.783696,106.694513)); // Quận 3
            locations.add(new LatLng(10.758672,106.700743)); // Quận 4
            locations.add(new LatLng(10.754692,106.678184)); // Quận 5
            locations.add(new LatLng(10.745787,106.625096)); // Quận 6
            locations.add(new LatLng(10.758229,106.743626)); // Quận 7
            locations.add(new LatLng(10.820911,106.772414)); // Quận 9
            locations.add(new LatLng(10.774077,106.668496)); // Quận 10
            locations.add(new LatLng(10.767479,106.653151)); // Quận 11
            locations.add(new LatLng(10.876358,106.647659)); // Quận 12
            locations.add(new LatLng(10.751916,106.612714)); // Quận Bình Tân
            locations.add(new LatLng(10.812216,106.690267)); // Quận Bình Thạnh
            locations.add(new LatLng(10.841511,106.664689)); // Quận Gò Vấp
            locations.add(new LatLng(10.794424,106.680929)); // Quận Phú Nhuận
            locations.add(new LatLng(10.808394,106.665610)); // Quận Tân Bình
            locations.add(new LatLng(10.786570,106.636715)); // Quận Tân Phú
            locations.add(new LatLng(10.848561,106.759263)); // Quận Thủ Đức
            locations.add(new LatLng(10.736472,106.689785)); // Huyện Bình Chánh
            locations.add(new LatLng(10.865326,106.613966)); // Huyện Hóc Môn
            for (LatLng latLng : locations){
                googleMap.addMarker(new MarkerOptions()
                        .position(latLng)
                        .title("The Coffee")
                        .icon(BitmapDescriptorFactory.fromBitmap(createMaker(getContext(),R.drawable.logo))));
            }
            LatLngBounds.Builder builder = new LatLngBounds.Builder();
            builder.include(locations.get(0)); // Điểm A
//            builder.include(locations.get(locations.size()-1)); // Điểm B
            LatLngBounds bounds = builder.build();
            CameraUpdate cu = CameraUpdateFactory.newLatLngBounds(bounds,100);
            googleMap.moveCamera(cu);
            googleMap.animateCamera(CameraUpdateFactory.zoomTo(15),2000,null);
            spn.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    switch (parent.getSelectedItemPosition()){
                        case 0: // Quận 1
                            LatLng sydney = new LatLng(10.768345, 106.698828);
                            googleMap.addMarker(new MarkerOptions().position(sydney)
                                    .icon(BitmapDescriptorFactory.fromBitmap(createMaker(getContext(),R.drawable.logo))));
                            CameraPosition cameraPosition = new CameraPosition.Builder().target(sydney).zoom(15).build();
                            googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
                            break;
                        case 1: // Quận 2
                            sydney = new LatLng(10.803691, 106.733104);
                            googleMap.addMarker(new MarkerOptions().position(sydney)
                                    .icon(BitmapDescriptorFactory.fromBitmap(createMaker(getContext(),R.drawable.logo))));
                            cameraPosition = new CameraPosition.Builder().target(sydney).zoom(15).build();
                            googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
                            break;
                        case 2: // Quận 3
                            sydney = new LatLng(10.783696, 106.694513);
                            googleMap.addMarker(new MarkerOptions().position(sydney)
                                    .icon(BitmapDescriptorFactory.fromBitmap(createMaker(getContext(),R.drawable.logo))));
                            cameraPosition = new CameraPosition.Builder().target(sydney).zoom(15).build();
                            googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
                            break;
                        case 3: // Quận 4
                            sydney = new LatLng(10.758672,106.700743);
                            googleMap.addMarker(new MarkerOptions().position(sydney)
                                    .icon(BitmapDescriptorFactory.fromBitmap(createMaker(getContext(),R.drawable.logo))));
                            cameraPosition = new CameraPosition.Builder().target(sydney).zoom(15).build();
                            googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
                            break;
                        case 4: // Quận 5
                            sydney = new LatLng(10.754692,106.678184);
                            googleMap.addMarker(new MarkerOptions().position(sydney)
                                    .icon(BitmapDescriptorFactory.fromBitmap(createMaker(getContext(),R.drawable.logo))));
                            cameraPosition = new CameraPosition.Builder().target(sydney).zoom(15).build();
                            googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
                            break;
                        case 5: // Quận 6
                            sydney = new LatLng(10.745787,106.625096);
                            googleMap.addMarker(new MarkerOptions().position(sydney)
                                    .icon(BitmapDescriptorFactory.fromBitmap(createMaker(getContext(),R.drawable.logo))));
                            cameraPosition = new CameraPosition.Builder().target(sydney).zoom(15).build();
                            googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
                            break;
                        case 6: // Quận 7
                            sydney = new LatLng(10.758229,106.743626);
                            googleMap.addMarker(new MarkerOptions().position(sydney)
                                    .icon(BitmapDescriptorFactory.fromBitmap(createMaker(getContext(),R.drawable.logo))));
                            cameraPosition = new CameraPosition.Builder().target(sydney).zoom(15).build();
                            googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
                            break;
                        case 7: // Quận 9
                            sydney = new LatLng(10.820911,106.772414);
                            googleMap.addMarker(new MarkerOptions().position(sydney)
                                    .icon(BitmapDescriptorFactory.fromBitmap(createMaker(getContext(),R.drawable.logo))));
                            cameraPosition = new CameraPosition.Builder().target(sydney).zoom(15).build();
                            googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
                            break;
                        case 8: // Quận 10
                            sydney = new LatLng(10.774077,106.668496);
                            googleMap.addMarker(new MarkerOptions().position(sydney)
                                    .icon(BitmapDescriptorFactory.fromBitmap(createMaker(getContext(),R.drawable.logo))));
                            cameraPosition = new CameraPosition.Builder().target(sydney).zoom(15).build();
                            googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
                            break;
                        case 9: // Quận 11
                            sydney = new LatLng(10.767479,106.653151);
                            googleMap.addMarker(new MarkerOptions().position(sydney)
                                    .icon(BitmapDescriptorFactory.fromBitmap(createMaker(getContext(),R.drawable.logo))));
                            cameraPosition = new CameraPosition.Builder().target(sydney).zoom(15).build();
                            googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
                            break;
                        case 10: // Quận 12
                            sydney = new LatLng(10.876358,106.647659);
                            googleMap.addMarker(new MarkerOptions().position(sydney)
                                    .icon(BitmapDescriptorFactory.fromBitmap(createMaker(getContext(),R.drawable.logo))));
                            cameraPosition = new CameraPosition.Builder().target(sydney).zoom(15).build();
                            googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
                            break;
                        case 11: // Quận Bình Tân
                            sydney = new LatLng(10.751916,106.612714);
                            googleMap.addMarker(new MarkerOptions().position(sydney)
                                    .icon(BitmapDescriptorFactory.fromBitmap(createMaker(getContext(),R.drawable.logo))));
                            cameraPosition = new CameraPosition.Builder().target(sydney).zoom(15).build();
                            googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
                            break;
                        case 12: // Quận Bình Thạnh
                            sydney = new LatLng(10.812216,106.690267);
                            googleMap.addMarker(new MarkerOptions().position(sydney)
                                    .icon(BitmapDescriptorFactory.fromBitmap(createMaker(getContext(),R.drawable.logo))));
                            cameraPosition = new CameraPosition.Builder().target(sydney).zoom(15).build();
                            googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
                            break;
                        case 13: // Quận Gò Vấp
                            sydney = new LatLng(10.841511,106.664689);
                            googleMap.addMarker(new MarkerOptions().position(sydney)
                                    .icon(BitmapDescriptorFactory.fromBitmap(createMaker(getContext(),R.drawable.logo))));
                            cameraPosition = new CameraPosition.Builder().target(sydney).zoom(15).build();
                            googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
                            break;
                        case 14: // Quận Phú Nhuận
                            sydney = new LatLng(10.794424,106.680929);
                            googleMap.addMarker(new MarkerOptions().position(sydney)
                                    .icon(BitmapDescriptorFactory.fromBitmap(createMaker(getContext(),R.drawable.logo))));
                            cameraPosition = new CameraPosition.Builder().target(sydney).zoom(15).build();
                            googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
                            break;
                        case 15: // Quận Tân Bình
                            sydney = new LatLng(10.808394,106.665610);
                            googleMap.addMarker(new MarkerOptions().position(sydney)
                                    .icon(BitmapDescriptorFactory.fromBitmap(createMaker(getContext(),R.drawable.logo))));
                            cameraPosition = new CameraPosition.Builder().target(sydney).zoom(15).build();
                            googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
                            break;
                        case 16: // Quận Tân Phú
                            sydney = new LatLng(10.786570,106.636715);
                            googleMap.addMarker(new MarkerOptions().position(sydney)
                                    .icon(BitmapDescriptorFactory.fromBitmap(createMaker(getContext(),R.drawable.logo))));
                            cameraPosition = new CameraPosition.Builder().target(sydney).zoom(15).build();
                            googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
                            break;
                        case 17: // Quận Thủ Đức
                            sydney = new LatLng(10.848561,106.759263);
                            googleMap.addMarker(new MarkerOptions().position(sydney)
                                    .icon(BitmapDescriptorFactory.fromBitmap(createMaker(getContext(),R.drawable.logo))));
                            cameraPosition = new CameraPosition.Builder().target(sydney).zoom(15).build();
                            googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
                            break;
                        case 18: // Huyện Bình Chánh
                            sydney = new LatLng(10.736472,106.689785);
                            googleMap.addMarker(new MarkerOptions().position(sydney)
                                    .icon(BitmapDescriptorFactory.fromBitmap(createMaker(getContext(),R.drawable.logo))));
                            cameraPosition = new CameraPosition.Builder().target(sydney).zoom(15).build();
                            googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
                            break;
                        case 19: // Huyện Hóc Môn
                            sydney = new LatLng(10.865326,106.613966);
                            googleMap.addMarker(new MarkerOptions().position(sydney)
                                    .icon(BitmapDescriptorFactory.fromBitmap(createMaker(getContext(),R.drawable.logo))));
                            cameraPosition = new CameraPosition.Builder().target(sydney).zoom(15).build();
                            googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
                            break;
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
//            LatLng sydney = new LatLng(10.947462, 106.830116);
//            googleMap.addMarker(new MarkerOptions().position(sydney).title("R132 Võ Thị Sáu").snippet("R132 Võ Thị Sáu")
//                    .icon(BitmapDescriptorFactory.fromBitmap(createMaker(getContext(),R.drawable.logo_map))));
//            // Tự động phóng to đến vị trí đã đánh dấu
//            CameraPosition cameraPosition = new CameraPosition.Builder().target(sydney).zoom(15).build();
//            googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
        });
        return view;
    }

    public static Bitmap createMaker(Context context, @DrawableRes int resource) {

        View marker = ((LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.custom_marker_layout, null);

        CircleImageView markerImage = (CircleImageView) marker.findViewById(R.id.user_dp);
        markerImage.setImageResource(resource);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        marker.setLayoutParams(new ViewGroup.LayoutParams(52, ViewGroup.LayoutParams.WRAP_CONTENT));
        marker.measure(displayMetrics.widthPixels, displayMetrics.heightPixels);
        marker.layout(0, 0, displayMetrics.widthPixels, displayMetrics.heightPixels);
        marker.buildDrawingCache();
        Bitmap bitmap = Bitmap.createBitmap(marker.getMeasuredWidth(), marker.getMeasuredHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        marker.draw(canvas);

        return bitmap;
    }
    // Cấp quyền
    public static boolean hasPermissions(Context context, String... permissions) {
        if (context != null && permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mMapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mMapView.onLowMemory();
    }
}