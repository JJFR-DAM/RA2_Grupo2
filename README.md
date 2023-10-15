# RA2_Grupo2

Thank you for downloading our program.

The program is finished.

This application has been designed for manage products, suppliers and do transactions storing data in MySQL from xampp a SQL database
using java swing interface.

To use the program correctly you must download and install xampp with the following link:
(https://www.apachefriends.org/es/download.html)
If you use Windows 64 bits, you can directly use this other link:
(https://sourceforge.net/projects/xampp/files/XAMPP%20Windows/8.2.4/xampp-windows-x64-8.2.4-0-VS16-installer.exe)

The program is configured with a default values to connect with the database user name -> "root" and default password -> "".
You can change this values in the class "com.RA2_Grupo2.methods/SQL_Methods.java".

You must run xampp press start MySQL and press admin button too to open the database. When you are in the page that manage the database you must
press import and select the ra2_grupo2.sql file to cread the database.

Once you have follow this steps, you can open the application. When you run test it open the login view when you can select between register or login
if you do the login, the application will open the inventory view, you can navigate between Product&Supplier or TransactionHistory views.

In Product&Supplier view you will find a selector to change between product and supply, buttons to CRUD the object. In product option, you can filter by
name, category or price.

In TransactionHistory view you can register positive or negative transactions and see every operation done.
