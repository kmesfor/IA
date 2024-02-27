package com.gmail.kianmesforush.tutormanagement.datatypes;

// Logical separation between Popups and Screens where Popups should not call ScreenManager#exit
// or exit the program in any other way. However, they function identically
public abstract class Popup extends Screen {
}
