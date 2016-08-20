package com.namh.drawerwithfragment.frag;

import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.namh.drawerwithfragment.R;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FourthFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FourthFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FourthFragment extends Fragment {




    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFourthFragmentInteraction(Uri uri);
    }

    ////////////////////////////////////////////////////////////
    //
    //
    //  Download Manager
    //
    //
    ////////////////////////////////////////////////////////////

    private BroadcastReceiver downloadCompleteReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            // check whether the download-id is mine
            long id = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, 0L);
            if (id != mDownloadId) {
                // not our download id, ignore
                return;
            }

            DownloadManager downloadManager = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);

            // make a query
            DownloadManager.Query query = new DownloadManager.Query();
            query.setFilterById(id);

            // check the status
            Cursor cursor = downloadManager.query(query);
            if (cursor.moveToFirst()) {
                // when download completed
                int statusColumn = cursor.getColumnIndex(DownloadManager.COLUMN_STATUS);
                if (DownloadManager.STATUS_SUCCESSFUL != cursor.getInt(statusColumn)) {
                    Log.w("FourthFragment", "Download Failed");
                    return;
                }

                int uriIndex = cursor.getColumnIndex(DownloadManager.COLUMN_LOCAL_URI);
                String downloadedPackageUriString = cursor.getString(uriIndex);

            }else{
                // when canceled
                return;
            }



        }
    };
    private Long mDownloadId = null;
    private IntentFilter downloadCompleteIntentFilter
            = new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE);


    ////////////////////////////////////////////////////////////
    //
    //
    //  Start here...
    //
    //
    ////////////////////////////////////////////////////////////

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;




    public FourthFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment OneFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FourthFragment newInstance(String param1, String param2) {
        FourthFragment fragment = new FourthFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    ////////////////////////////////////////////////////////////
    //
    //
    //  Overrides
    //
    //
    ////////////////////////////////////////////////////////////

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // for options menu
        setHasOptionsMenu(true);

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // inflate
        View view = inflater.inflate(R.layout.fragment_fourth, container, false);

        _setAppBar(view);
        _setFab(view);
        _setDownloadManager(view);


        // Inflate the layout for this fragment
        return view;
    }




    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    // Menu

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.main, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }else if (id == android.R.id.home){
            // open the Drawer menu
            DrawerLayout drawer = (DrawerLayout) getActivity().findViewById(R.id.drawer_layout);
            drawer.openDrawer(GravityCompat.START);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    ////////////////////////////////////////////////////////////
    //
    //
    //  methods
    //
    //
    ////////////////////////////////////////////////////////////

    private void _setAppBar(View view) {
        // toolbar
        Toolbar toolbar = (Toolbar)view.findViewById(R.id.toolbar);
        AppCompatActivity atvt = (AppCompatActivity)getActivity();
        atvt.setSupportActionBar(toolbar);

        // back button
        ActionBar ab = atvt.getSupportActionBar();
        ab.setHomeAsUpIndicator(R.drawable.ic_menu_black_24dp);
        ab.setDisplayHomeAsUpEnabled(true);
    }

    private void _setFab(View view) {
        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    private void _setDownloadManager(View view) {
        // set download-complete filter
        this.getContext().registerReceiver(downloadCompleteReceiver, downloadCompleteIntentFilter);

        _setDownloadButton(view);
        _setCancelButton(view);

    }

    private void _setDownloadButton(View view) {
        Button btn = (Button)view.findViewById(R.id.btn_download);

        final Context ctx = this.getContext();
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fUrl = "http://upload.wikimedia.org/wikipedia/commons/c/cf/Frog_on_river_4000x3000_26-09-2010_11-01am_2mb.jpg";

                String dlpath = "myfiles";
                String newFilename = dlpath + "/TestImage.jpg";

                // Make a request
                DownloadManager.Request request
                        = new DownloadManager.Request(Uri.parse(fUrl))
                        .setAllowedOverRoaming(false)
                        .setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI)
                        .setTitle(newFilename)
                        .setDescription("ProcessUpdaterExmpale");


                if (android.os.Environment.getExternalStorageState()
                        .equals(android.os.Environment.MEDIA_MOUNTED)) {
                    request.setDestinationInExternalPublicDir(
                            android.os.Environment.DIRECTORY_DOWNLOADS, newFilename);
                }

                // you can hide download status
                // request.setVisibleInDownloadsUi(false);
                // request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_HIDDEN);

                // enqueue
                DownloadManager dm = (DownloadManager) ctx.getSystemService(Context.DOWNLOAD_SERVICE);
                mDownloadId = dm.enqueue(request);

            }
        });
    }


    private void _setCancelButton(View view) {
        Button btn = (Button)view.findViewById(R.id.btn_cancel);

        final Context ctx = this.getContext();
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // cancel
                if(mDownloadId != null) {
                    ((DownloadManager) ctx.getSystemService(Context.DOWNLOAD_SERVICE)).remove(mDownloadId);
                }
            }
        });
    }


    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFourthFragmentInteraction(uri);
        }
    }





    ////////////////////////////////////////////////////////////
    //
    //
    //  Implements
    //
    //
    ////////////////////////////////////////////////////////////

}
