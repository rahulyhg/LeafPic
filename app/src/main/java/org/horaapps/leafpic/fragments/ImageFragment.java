package org.horaapps.leafpic.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.signature.StringSignature;
import com.github.chrisbanes.photoview.PhotoView;

import org.horaapps.leafpic.R;
import org.horaapps.leafpic.data.Media;
import org.horaapps.leafpic.views.RotateTransformation;

import java.util.Date;



/**
 * Created by dnld on 18/02/16.
 */

@SuppressWarnings("ResourceType")
public class ImageFragment extends Fragment {

    View view;
    private Media img;
    private final String TAG = "asd";

    public static ImageFragment newInstance(Media media) {
        ImageFragment imageFragment = new ImageFragment();
        Bundle args = new Bundle();
        args.putParcelable("image", media);
        imageFragment.setArguments(args);
        return imageFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        img = getArguments().getParcelable("image");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_photo, container, false);


        /*if (Hawk.get(getString(R.string.preference_sub_scaling), true)) {
            SubsamplingScaleImageView imageView = new SubsamplingScaleImageView(getContext());
            imageView.setImage(ImageSource.uri(img.getUri()).tilingEnabled());
            imageView.setOrientation(SubsamplingScaleImageView.ORIENTATION_0);
            imageView.setOnClickListener(v -> ((SingleMediaActivity) getActivity()).toggleSystemUI());
            return imageView;
        } else {
            PhotoView photoView = new PhotoView(getContext());
            displayMedia(photoView, true);
            photoView.setOnClickListener(view -> ((SingleMediaActivity) getActivity()).toggleSystemUI());
            photoView.setMaximumScale(5.0F);
            photoView.setMediumScale(3.0F);

            return photoView;
        }*/

        return view;
    }

    private void loadImage() {

    }

   /* private void rotateLoop() { //april fools
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                photoView.setRotationBy(1);
                rotateLoop();
            }
        }, 5);
    }*/

    private void displayMedia(PhotoView photoView, boolean useCache) {
        //PreferenceUtil SP = PreferenceUtil.make(getContext());

        Glide.with(getContext())
                .load(img.getUri())
                .asBitmap()
                .signature(useCache ? img.getSignature(): new StringSignature(new Date().getTime()+""))
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .thumbnail(0.5f)
                .transform(new RotateTransformation(getContext(), img.getOrientation(), false))
                .animate(R.anim.fade_in)
                .into(photoView);

    }

    public boolean rotatePicture(int rotation) {
        // TODO: 28/08/16 not working yet
        /*PhotoView photoView = (PhotoView) getView();

        int orientation = Measure.rotateBy(img.getOrientation(), rotation);
        Log.wtf("asd", img.getOrientation()+" + "+ rotation+" = " +orientation);

        if(photoView != null && img.setOrientation(orientation)) {
            Glide.clear(photoView);
            Glide.with(getContext())
                    .load(img.getUri())
                    .asBitmap()
                    .signature(img.getSignature())
                    .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                    //.thumbnail(0.5f)
                    .transform(new RotateTransformation(getContext(), rotation , true))
                    .into(photoView);

            return true;
        }*/
        return false;
    }
}