## Repository structure

This repository includes two directories:

1. Coursework: This directory includes the learning diary and all material from the exercise projects that were done during this course. These include mostly the Android Developer course apps. All of those apps/projects are in their own folders (they include Affirmations, Dogglers, Lemonade, TipCalculator and Words).

2. Pue: This directory includes the project that was done during this course (as a main task). You can download the whole repository and open any of the folders as a project in Android Studio and they should work fine.

---

## How to run/use the project app Pue

This app can be run from Android Studio (in a virtual device). Or if you want to, the app can be compiled as apk and installed on an Android Device.

## Main Objective of the app (how to use it)

The apps main objective is to be used as a learning method in childcare to teach children how to dress themselves and in which order should different clothing items be put on.

The app starts in MainActivity screen where a list of clothing items are presented. The layout can be changed from the menu button (top right corner) as a grid layout. In this screen the user (kindergarten teacher) chooses the clothing items that the child should dress. The chosen items are indicated as selected cards.

When all items that want to be dressed are chosen the user clicks the "Siirry pukemaan" button, which takes the user to second screen (DressingActivity). In this screen the layout is by default a grid, but can be changed again from the menu button. This screen shows the chosen clothing items that are arranged in the usual dressing order (this was important, because the children must be taught in which order the items should be put on). The order can be rearranged by holding and dragging a card to different location. After the order is approved by the teacher, the device can be handed over to the child. When an item is put on the child (or the teacher) clicks on the item and it flips around showing a thumb up button. This can be reversed by clicking the card again. After all clothing items have been put on, the user can click the "Valmis" button, which takes him/her to the third screen (FinalActivity).

In the final screen only a thumbs up figure is shown with congratulatory text. In this screen there is a button "Palaa alkuun" that takes the user to the first screen and resets the chosen clothing items.
