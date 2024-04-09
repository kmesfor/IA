package org.candidates.ibo.kgb197.tutormanagement.datatypes;

/**
 * An abstract class that serves the same purpose as Screen,
 * a different class was created to have a logical separation
 * between a Screen and ScreenPopup which serve different purposes.
 * ScreenPopup objects should not call ScreenManager#exit() or
 * exit the program in any other way.
 */
public abstract class ScreenPopup extends Screen {
}
