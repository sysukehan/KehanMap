package com.study.android.kehan.kehanmap.View;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.LogoPosition;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.map.PolygonOptions;
import com.baidu.mapapi.map.Stroke;
import com.baidu.mapapi.map.TextOptions;
import com.baidu.mapapi.map.UiSettings;
import com.baidu.mapapi.model.LatLng;
import com.study.android.kehan.kehanmap.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kehan on 16-7-7.
 */
public class MainActivity extends Activity {

    private final String TAG = "MainActivity";

    private MapView mMapView = null;
    private BaiduMap mBaiduMap = null;
    private UiSettings mUiSettings = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //  设置全屏
//        int flag = WindowManager.LayoutParams.FLAG_FULLSCREEN;  //  定义全屏参数
//        Window window = MainActivity.this.getWindow();  //  获得当前窗体对象
//        window.setFlags(flag, flag);  //  设置当前窗体为全屏显示

        //  在使用SDK各组件之前初始化context信息，传入ApplicationContext
        //  注意该方法要再setContentView方法之前实现
        SDKInitializer.initialize(getApplicationContext());
        setContentView(R.layout.main_layout);
        //  获取地图控件引用
        mMapView = (MapView) findViewById(R.id.bmapView);
        mBaiduMap = mMapView.getMap();
        mUiSettings = mBaiduMap.getUiSettings();

        //  普通地图
//        mBaiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);

        //  卫星地图
//        mBaiduMap.setMapType(BaiduMap.MAP_TYPE_SATELLITE);

        //  空白地图, 基础地图瓦片将不会被渲染。在地图类型中设置为NONE，将不会使用流量下载基础地图瓦片图层。
        //  使用场景：与瓦片图层一起使用，节省流量，提升自定义瓦片图下载速度。
        //  不是很明白上面在说什么，空白地图什么都没有
//        mBaiduMap.setMapType(BaiduMap.MAP_TYPE_NONE);

        //  开启实时交通图
//        mBaiduMap.setTrafficEnabled(true);

        //  开启百度城市热力图
//        mBaiduMap.setBaiduHeatMapEnabled(true);

        //  设置地图logo显示的位置
        //  默认左下角，可设置为左下，中下，右下，左上，中上，右上
        //  文档中写地图logo不允许被遮挡，实际上在布局中是可以遮挡的，这句话应该只是百度地图对开发者的要求吧
//        mMapView.setLogoPosition(LogoPosition.logoPostionleftTop);

        //  对地图功能的一些设置通过UiSettings类来完成
//        mUiSettings.setAllGesturesEnabled(true);  //  设置所有手势可用

        //  显示具有入场动画效果的marker
//        setAnimateMarker(39.963175, 116.400244);

        //  隐藏地图标注，可得到仅显示道路信息的地图（说是显示道路信息，道路上的字也没有了= =）
//        mBaiduMap.showMapPoi(false);

        //  在地图上绘制五边形
//         drawGraph();

        //  在地图上添加文字覆盖物
//        setTextCover(39.963175, 116.400244, "我爱北京天安门");


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
        mMapView.onDestroy();
    }
    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView. onResume ()，实现地图生命周期管理
        mMapView.onResume();
    }
    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView. onPause ()，实现地图生命周期管理
        mMapView.onPause();
    }

    /**
     * 在地图指定位置作标记
     * @param latitude  纬度
     * @param longtitude  经度
     */
    private void setMapMarker(double latitude, double longtitude) {
        LatLng point = new LatLng(latitude, longtitude);  //  定义Maker坐标点
        BitmapDescriptor bitmap = BitmapDescriptorFactory.fromResource(R.mipmap.baidumap_icon);  //  构建Marker图标
        OverlayOptions option = new MarkerOptions().position(point).icon(bitmap).zIndex(9).draggable(true);  //构建MarkerOption，用于在地图上添加Marker
        mBaiduMap.addOverlay(option);  //  在地图上添加Marker，并显示
    }

    /**
     * 在地图上做有动画的标记
     * @param latitude
     * @param longtitude
     */
    private void setAnimateMarker(double latitude, double longtitude) {
        //  period用于设置多少帧刷新一次图片资源，值越小动画越快
        MarkerOptions ooD = new MarkerOptions().position(new LatLng(latitude, longtitude))
                .icon(BitmapDescriptorFactory.fromResource(R.mipmap.baidumap_icon))
                .zIndex(0).period(10);
        ooD.animateType(MarkerOptions.MarkerAnimateType.grow);  //  生长动画
//        ooD.animateType(MarkerOptions.MarkerAnimateType.drop);  //  降落动画
        Marker mMarkerD = (Marker) (mBaiduMap.addOverlay(ooD));
    }

    /**
     * 在地图上绘制几何图形
     */
    private void drawGraph() {
        //  定义多边形的五个顶点
        LatLng pt1 = new LatLng(39.93923, 116.357428);
        LatLng pt2 = new LatLng(39.91923, 116.327428);
        LatLng pt3 = new LatLng(39.89923, 116.347428);
        LatLng pt4 = new LatLng(39.89923, 116.367428);
        LatLng pt5 = new LatLng(39.91923, 116.387428);
        List<LatLng> pts = new ArrayList<LatLng>();
        pts.add(pt1);
        pts.add(pt2);
        pts.add(pt3);
        pts.add(pt4);
        pts.add(pt5);
        //  构建用户绘制多边形的Option对象
        //  Stroke 边框类，构造函数Stroke(int strokeWidth, int color)，宽度默认为5px，颜色为十六进制RGB值
        OverlayOptions polygonOption = new PolygonOptions()
                .points(pts)
                .stroke(new Stroke(3,getResources().getColor(R.color.blue_500)))
                .fillColor(getResources().getColor(R.color.blue_100));
        //  在地图上添加多边形Option，用于显示
        mBaiduMap.addOverlay(polygonOption);
    }

    /**
     * 设置文字覆盖物
     * @param latitude 经度
     * @param longtitude 纬度
     * @param content 文字内容
     */
    private void setTextCover(double latitude, double longtitude, String content) {
        LatLng llText = new LatLng(latitude, longtitude);

        OverlayOptions textOption = new TextOptions()
                .bgColor(mGetColor(R.color.blue_300))
                .fontColor(mGetColor(R.color.red_700))
                .text(content).fontSize(50).position(llText);
        mBaiduMap.addOverlay(textOption);
    }

    private int mGetColor(int colorId) {
        return getResources().getColor(colorId);
    }
}
