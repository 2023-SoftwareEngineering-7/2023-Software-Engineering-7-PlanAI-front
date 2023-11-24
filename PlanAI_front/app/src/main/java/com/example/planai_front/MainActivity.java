//package com.example.taskbutton;
//
//import android.content.Context;
//import android.os.Bundle;
//import android.view.MenuItem;
//import android.view.View;
//import android.widget.PopupMenu;
//import android.widget.Toast;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.appcompat.view.ContextThemeWrapper;

//public class MainActivity extends AppCompatActivity {
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        // Find the main button
//        final View mainButton = findViewById(R.id.fab_main);
//
//        // Set onClickListener for the main button
//        mainButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // Create a PopupMenu with the custom style
//                Context wrapper = new ContextThemeWrapper(MainActivity.this, R.style.PopupMenuStyle);
//                PopupMenu popupMenu = new PopupMenu(wrapper, mainButton);
//                popupMenu.getMenuInflater().inflate(R.menu.popup_menu, popupMenu.getMenu());
//
//                // Set item click listener for the secondary options
//                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
//                    @Override
//                    public boolean onMenuItemClick(MenuItem item) {
//                        // Handle item clicks
//                        int id = item.getItemId();
//                        if (id == R.id.menu_task) {
//                            // Handle Task click
//                            showToast("Task clicked");
//                            return true;
//                        } else if (id == R.id.menu_schedule) {
//                            // Handle Schedule click
//                            showToast("Schedule clicked");
//                            return true;
//                        } else if (id == R.id.menu_other) {
//                            // Handle Other click
//                            showToast("Other clicked");
//                            return true;
//                        } else {
//                            return false;
//                        }
//                    }
//                });
//
//                // Show the PopupMenu
//                popupMenu.show();
//            }
//        });
//    }
//
//    private void showToast(String message) {
//        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
//    }
//}


//package com.example.taskbutton;
//
//import android.app.Dialog;
//import android.content.Context;
//import android.os.Bundle;
//import android.util.DisplayMetrics;
//import android.view.MenuItem;
//import android.view.View;
//import android.view.Window;
//import android.widget.Button;
//import android.widget.PopupMenu;
//import android.widget.Toast;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//public class MainActivity extends AppCompatActivity {
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        // Find the main button
//        final View mainButton = findViewById(R.id.fab_main);
//
//        // Set onClickListener for the main button
//        mainButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // Create a PopupMenu
//                PopupMenu popupMenu = new PopupMenu(MainActivity.this, mainButton);
//                popupMenu.getMenuInflater().inflate(R.menu.popup_menu, popupMenu.getMenu());
//
//                // Set item click listener for the secondary options
//                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
//                    @Override
//                    public boolean onMenuItemClick(MenuItem item) {
//                        // Handle item clicks
//                        int id = item.getItemId();
//                        if (id == R.id.menu_task) {
//                            // Handle Task click
//                            showPopup("Task clicked");
//                            return true;
//                        } else if (id == R.id.menu_schedule) {
//                            // Handle Schedule click
//                            showPopup("Schedule clicked");
//                            return true;
//                        } else if (id == R.id.menu_other) {
//                            // Handle Other click
//                            showPopup("Other clicked");
//                            return true;
//                        } else {
//                            return false;
//                        }
//                    }
//                });
//
//                // Show the PopupMenu
//                popupMenu.show();
//            }
//        });
//    }
//
//    private void showPopup(String message) {
//        // Create a Dialog
//        final Dialog dialog = new Dialog(MainActivity.this);
//        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
//        dialog.setContentView(R.layout.popup_layout);
//
//        // Set the animation for the dialog
//        dialog.getWindow().getAttributes().windowAnimations = R.style.SlideUpDownAnimation;
//
//        // Convert dp to pixels
//        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
//        int width = Math.round(400 * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT)); // Width in dp
//        int height = Math.round(500 * (displayMetrics.ydpi / DisplayMetrics.DENSITY_DEFAULT)); // Height in dp
//
//        // Set the width and height of the Dialog
//        dialog.getWindow().setLayout(width, height);
//
//        // Find the close button
//        Button closeButton = (Button) dialog.findViewById(R.id.close_button);
//
//        // Set onClickListener for the close button
//        closeButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                dialog.dismiss();
//            }
//        });
//
//        // Show the Dialog
//        dialog.show();
//    }
//}






//public class MainActivity extends AppCompatActivity {
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        // Find the main button
//        final View mainButton = findViewById(R.id.fab_main);
//
//        // Set onClickListener for the main button
//        mainButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // Create a PopupMenu
//                PopupMenu popupMenu = new PopupMenu(MainActivity.this, mainButton);
//                popupMenu.getMenuInflater().inflate(R.menu.popup_menu, popupMenu.getMenu());
//
//                // Set item click listener for the secondary options
//                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
//                    @Override
//                    public boolean onMenuItemClick(MenuItem item) {
//                        // Handle item clicks
//                        int id = item.getItemId();
//                        if (id == R.id.menu_task) {
//                            // Handle Task click
//                            showBottomSheet("Task clicked");
//                            return true;
//                        } else if (id == R.id.menu_schedule) {
//                            // Handle Schedule click
//                            showBottomSheet("Schedule clicked");
//                            return true;
//                        } else if (id == R.id.menu_other) {
//                            // Handle Other click
//                            showBottomSheet("Other clicked");
//                            return true;
//                        } else {
//                            return false;
//                        }
//                    }
//                });
//
//                // Show the PopupMenu
//                popupMenu.show();
//            }
//        });
//    }
//
//    private void showBottomSheet(String message) {
//        // Create a BottomSheetDialog
//        BottomSheetDialog bottomSheet = new BottomSheetDialog(MainActivity.this);
//        bottomSheet.setContentView(R.layout.fragment_bottom_sheet_dialog);
//
//        // Find the TextView in the bottom sheet layout
//        TextView textView = bottomSheet.findViewById(R.id.text_view);
//        textView.setText(message);
//
//        // Show the BottomSheetDialog
//        bottomSheet.show();
//    }
//}


//public class MainActivity extends AppCompatActivity {
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        // Find the main button
//        final View mainButton = findViewById(R.id.fab_main);
//
//        // Set onClickListener for the main button
//        mainButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // Create a PopupMenu
//                PopupMenu popupMenu = new PopupMenu(MainActivity.this, mainButton);
//                popupMenu.getMenuInflater().inflate(R.menu.popup_menu, popupMenu.getMenu());
//
//                // Set item click listener for the secondary options
//                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
//                    @Override
//                    public boolean onMenuItemClick(MenuItem item) {
//                        // Handle item clicks
//                        int id = item.getItemId();
//                        if (id == R.id.menu_task) {
//                            // Handle Task click
//                            showBottomSheet("We did it!");
//                            return true;
//                        } else if (id == R.id.menu_schedule) {
//                            // Handle Schedule click
//                            showBottomSheet("We did it!");
//                            return true;
//                        } else if (id == R.id.menu_other) {
//                            // Handle Other click
//                            showBottomSheet("We did it!");
//                            return true;
//                        } else {
//                            return false;
//                        }
//                    }
//                });
//
//                // Show the PopupMenu
//                popupMenu.show();
//            }
//        });
//    }
//
//    private void showBottomSheet(String message) {
//        // Create a BottomSheetDialog
//        BottomSheetDialog bottomSheet = new BottomSheetDialog(MainActivity.this);
//        bottomSheet.setContentView(R.layout.fragment_bottom_sheet_dialog);
//
//        // Find the TextView in the bottom sheet layout
//        TextView textView = bottomSheet.findViewById(R.id.text_view);
//        textView.setText(message);
//
//        // Show the BottomSheetDialog
//        bottomSheet.show();
//    }
//}

//package com.example.taskbutton;
//
//import android.os.Bundle;
//import android.widget.TextView;
//import android.view.View;
//import android.view.MenuItem;
//import android.widget.PopupMenu;
//import androidx.appcompat.app.AppCompatActivity;
//import com.google.android.material.bottomsheet.BottomSheetBehavior;
//import android.widget.LinearLayout;
//import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
//import com.google.android.material.bottomsheet.BottomSheetDialog;
//public class MainActivity extends AppCompatActivity {
//
//    private BottomSheetBehavior bottomSheetBehavior;
//    private TextView textView;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        // Find the bottom sheet and the text view
//        LinearLayout bottomSheet = findViewById(R.id.bottom_sheet);
//        textView = findViewById(R.id.text_view);
//
//        // Get the bottom sheet behavior
//        bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet);
//
//        // Set the peek height (optional)
//        bottomSheetBehavior.setPeekHeight(300);
//
//        // Find the main button
//        final View mainButton = findViewById(R.id.fab_main);
//
//        // Set onClickListener for the main button
//        mainButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // Create a PopupMenu
//                PopupMenu popupMenu = new PopupMenu(MainActivity.this, mainButton);
//                popupMenu.getMenuInflater().inflate(R.menu.popup_menu, popupMenu.getMenu());
//
//                // Set item click listener for the secondary options
//                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
//                    @Override
//                    public boolean onMenuItemClick(MenuItem item) {
//                        // Handle item clicks
//                        int id = item.getItemId();
//                        if (id == R.id.menu_task) {
//                            // Handle Task click
//                            showBottomSheet("We did it!");
//                            return true;
//                        } else if (id == R.id.menu_schedule) {
//                            // Handle Schedule click
//                            showBottomSheet("We did it!");
//                            return true;
//                        } else if (id == R.id.menu_other) {
//                            // Handle Other click
//                            showBottomSheet("We did it!");
//                            return true;
//                        } else {
//                            return false;
//                        }
//                    }
//                });
//
//                // Show the PopupMenu
//                popupMenu.show();
//            }
//        });
//    }
//
//    private void showBottomSheet(String message) {
//        // Set the text view text
//        textView.setText(message);
//
//        // Expand the bottom sheet
//        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
//    }
//}

package com.example.taskbutton;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.TextView;
import com.example.taskbutton.R;


import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private PopupWindow popupWindow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final View mainButton = findViewById(R.id.fab_main);

        mainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopup(mainButton);
            }
        });
    }

    private void showPopup(View anchorView) {
        View popupView = getLayoutInflater().inflate(R.layout.popup_layout, null);

        TextView textView = popupView.findViewById(R.id.popup_text_view);
        textView.setText("Popup content goes here.");

        Button closeButton = popupView.findViewById(R.id.close_button);
        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });

        int width = 1000; // set your desired width in pixels
        int height = 800; // set your desired height in pixels

        popupWindow = new PopupWindow(
                popupView,
                width,
                height,
                true
        );

        // Set an elevation value for a drop shadow
        popupWindow.setElevation(20);

        // Set a background drawable with rounded corners
        popupWindow.setBackgroundDrawable(getResources().getDrawable(R.drawable.popup_background));

        // Calculate the x offset to center the popup horizontally
        int xOffset = (anchorView.getWidth() - width) / 1;

        // Show the popup centered below the anchor view
        popupWindow.showAsDropDown(anchorView, xOffset, -anchorView.getHeight());
    }


}

