# Credit Application System
this app is made for handling costumers who assumed to withdraw a credit
from the bank ,so It's calculating their budget and whether they can  return the amount or not
(in this app that calculation are made according to the ID card number's last digit just to not have a strategy to do that
and to simulate the reality I also made the ID numbers that end with odd digit INVALID numbers)

## The usage of (frontend)
- add a user from the button at the Up_Left corner adding their name ,identity number ,phone number and salary.
- edit a user by clicking on *Edit* button from It's relevant line. 
- delete a user by clicking on *Delete* button from It's relevant line.
- show the user credit info by clicking on *Credit Info* button 
- if the user doesn't have applied, yet we can apply from the same button ,a window will pop up to make us do that
- for sorting the users' data we just have to click on the relevant column head to re-sort the table according that column ,to reverse the sorting process we just click on the same header once more.
- for sorting the data according the Date they are made we have to click on the header named 'sort by Date'. 
- additionally we can change background_mode by clicking on the switcher at the Up_Right button

the windows are like :

![The main page](images/MainPageSorting.jpg)
![The modals](images/pop_ups.png)

## The usage of (api)
|                                  | method | mapping                                      | path variables             | request parameters                                                                                                                                                                                                                                        | 
|----------------------------------|--------|----------------------------------------------|----------------------------|-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| get users                        | GET    | */json/users*                                | X                          | <ul><li>sortBy=**databaseId** *is set by default* alternatively you can choose **fullName** ,**identityNumber** ,**salary** or **phoneNumber**.</li><li>ascending=**true** *is set by default* you can type **false** te get reversed sorting  </li></ul> |
| get user By Database Id          | GET    | */json/Database_ID/{**databaseId**}*         | databaseId :int 64         | X                                                                                                                                                                                                                                                         |
| get user By Identity card number | GET    | */json/National_ID/{**identityCardNumber**}* | identityCardNumber :int 64 | X                                                                                                                                                                                                                                                         |
| save user                        | POST   | */json/users*                                | X                          | X , request body is needed                                                                                                                                                                                                                                |
| update user                      | PUT    | */json/users/{**databaseId**}*               | databaseId :int 64         | X , request body is needed                                                                                                                                                                                                                                |
| delete user                      | DELETE | */json/users/{**databaseId**}*               | databaseId :int 64         | X                                                                                                                                                                                                                                                         |

### request body
````
{
"identityNumber": 55555555555,  // 11 digits
"fullName": "String name",
"salary": 0,
"phoneNumber": 5555555555  // 10 digits
}
````