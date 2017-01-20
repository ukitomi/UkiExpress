## Synopsis
A small java app built by using Eclipse.

Please do citation if you are taking any of the code from my GitHub. Thank you!

If you want to know more about the project, [contact me!](https://www.linkedin.com/in/qiqiyuki-ou)

## Run the program
The program takes two arguments, one is the main java file and the other one is a text file.

Simply navigate to the folder destination on command prompt, e.g., C:\Users\Yuki\Documents\UkiExpress.

Then, run the following command lines:

C:\Users\Yuki\Documents\UkiExpress\java UkiExpress filename.txt

## How does the program work?
The file that the program read is like a menu. The menu contains the item's name and price for the item.

All item will be shown on the left. Click any of the item then it will be added to the order.

The selected item and price for the item will be shown on the right side.

When finished ordering, enter credit card number to process the order.

Click "cancel" button to restart the order.

Click "confirm" button after the credit card number is entered.

If the credit card is not valid, the order would not be process and the whole order has to restart all over.

## Test Cases
There are different test cases included within the folder.

1. Menu.txt: Contain the menu, with name and price separated by a bar |.

2. invalidprice.txt: The price in the menu contains invalid characters.

3. noinput.txt: Empty text file.

4. noname.txt: The menu does not contain a name, only price.

5. noprice.txt: One of the item in the menu does not contain price.


