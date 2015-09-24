package com.Schulprojekt.helloprojekt;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.Schulprojekt.helloprojekt.GUILogik.User;

public class ChatActivity extends Activity implements
		NavigationDrawerFragment.NavigationDrawerCallbacks {

	/**
	 * Fragment managing the behaviors, interactions and presentation of the
	 * navigation drawer.
	 */
	private NavigationDrawerFragment mNavigationDrawerFragment;

	/**
	 * Used to store the last screen title. For use in
	 * {@link #restoreActionBar()}.
	 */
	private CharSequence mTitle;
	private ImageView imageViewGame;
	private User loggedUser;
	private User chatPartner;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_chat);

		mNavigationDrawerFragment = (NavigationDrawerFragment) getFragmentManager()
				.findFragmentById(R.id.navigation_drawer);
		mTitle = getTitle();

		// Set up the drawer.
		mNavigationDrawerFragment.setUp(R.id.navigation_drawer,
				(DrawerLayout) findViewById(R.id.drawer_layout));
		
		//getActionBar().setIcon(R.drawable.dummycontact);
		imageViewGame = (ImageView) findViewById(R.id.imageViewGame);
		imageViewGame.setOnClickListener(new View.OnClickListener(){
			public void onClick(View v) {
				Intent i = new Intent(ChatActivity.this,
		        		SelectGameActivity.class);													
				Bundle b = new Bundle();															//Erstellen eines Bundles
				b.putString("loggedUser", loggedUser.getAccountName());							    //Füllen des Bundles mit Key und dem dazugehörigen Wert
				b.putString("chatPartner", chatPartner.getAccountName());
				i.putExtras(b);																		//Bundle ins Intent hinzufügen
				startActivity(i);
			}
		});
//		Bundle bundle = getIntent().getExtras();		
//		@SuppressWarnings("deprecation")
//		BitmapDrawable bmp = new BitmapDrawable(BitmapFactory.decodeByteArray(bundle.getByteArray("ContactImage"), 0, bundle.getByteArray("ContactImage").length));
//		getActionBar().setIcon(bmp);
		
		
		((View) getActionBar().getTitle()).setOnClickListener(new View.OnClickListener(){
			public void onClick(View v) {
				try{
//					DefaultHttpClient httpClient = new DefaultHttpClient();
//					HttpGet request = new HttpGet(SERVICE_URI + "/GetUserByAccountName/" + txtContactSearch);
//					
//					request.setHeader("Accept", "application/json");
//			        request.setHeader("Content-type", "application/json");
//
//			        HttpResponse response = httpClient.execute(request);
//			        
//			        HttpEntity responseEntity = response.getEntity();
//			        
//			        // Read response data into buffer
//			        char[] buffer = new char[(int)responseEntity.getContentLength()];
//			        InputStream stream = responseEntity.getContent();
//			        InputStreamReader reader = new InputStreamReader(stream);
//			        reader.read(buffer);
//			        stream.close();
//
//			        JSONObject user = new JSONObject(new String(buffer));
//			        
//			        // Populate text fields
//			        txtContactSearch.setText(user.getString("accountName"));
//			        
//			        if(txtContactSearch == null){
//			        	Toast.makeText(ContactSearchActivity.this, "Benutzer ist nicht vorhanden!", Toast.LENGTH_LONG).show();
//			        }else{
//			        	Intent i = new Intent(ContactSearchActivity.this, UserProfileActivity.class);
//			        	
//			        	Bundle b = new Bundle();
//			        	b.putString("userId", user.getString("userId"));
//			        	b.putString("accountName", user.getString("accountName"));
//			        	b.putString("aliasName", user.getString("aliasName"));
//			        	b.putString("password", user.getString("password"));
//			        	b.putBoolean("accountState", user.getString("accountState"));
//			        	b.putString("expierencePoints", user.getString("expierencePoints"));
//			        	b.putByteArray("picture", user.getString("picture"));
			       
			        	Bundle b = new Bundle();
			        	Intent i = new Intent(ChatActivity.this, UserProfileActivity.class);
			        	b.putString("userId", "");
			        	b.putString("accountName", "test");
			        	b.putString("aliasName", "test");
			        	b.putString("password", "test");
			        	b.putBoolean("accountState", true);
//			        	b.putString("expierencePoints", "...");
			        	b.putByteArray("picture", new byte[]{50});
			        	
						i.putExtras(b);
						startActivity(i);
						finish();
						System.exit(0);
//			        }     
				}catch (Exception e){
					e.printStackTrace();
				}
			}
		});
	}
	
	
	
	@Override
	public void onNavigationDrawerItemSelected(int position) {
		// update the main content by replacing fragments
		FragmentManager fragmentManager = getFragmentManager();
		fragmentManager
				.beginTransaction()
				.replace(R.id.container,
						PlaceholderFragment.newInstance(position + 1)).commit();
	}

	public void onSectionAttached(int number) {
		switch (number) {
		case 1:
			mTitle = getString(R.string.title_section1);
			break;
		case 2:
			mTitle = getString(R.string.title_section2);
			break;
		case 3:
			mTitle = getString(R.string.title_section3);
			break;
		}
	}

	public void restoreActionBar() {
		ActionBar actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
		actionBar.setDisplayShowTitleEnabled(true);
		actionBar.setTitle(mTitle);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		if (!mNavigationDrawerFragment.isDrawerOpen()) {
			// Only show items in the action bar relevant to this screen
			// if the drawer is not showing. Otherwise, let the drawer
			// decide what to show in the action bar.
			getMenuInflater().inflate(R.menu.chat, menu);
			restoreActionBar();
			return true;
		}
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {
		/**
		 * The fragment argument representing the section number for this
		 * fragment.
		 */
		private static final String ARG_SECTION_NUMBER = "section_number";

		/**
		 * Returns a new instance of this fragment for the given section number.
		 */
		public static PlaceholderFragment newInstance(int sectionNumber) {
			PlaceholderFragment fragment = new PlaceholderFragment();
			Bundle args = new Bundle();
			args.putInt(ARG_SECTION_NUMBER, sectionNumber);
			fragment.setArguments(args);
			return fragment;
		}

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_chat, container,
					false);
			return rootView;
		}

		@Override
		public void onAttach(Activity activity) {
			super.onAttach(activity);
			((ChatActivity) activity).onSectionAttached(getArguments().getInt(
					ARG_SECTION_NUMBER));
		}
	}

}
