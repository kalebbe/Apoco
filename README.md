[logo]: https://github.com/kalebbe/Apoco/blob/master/WebContent/assets/img/presentation/Apoco.png
[global]: https://github.com/kalebbe/Apoco/blob/master/WebContent/assets/img/presentation/Global.png
[dating]: https://github.com/kalebbe/Apoco/blob/master/WebContent/assets/img/presentation/Dating.png
[business]: https://github.com/kalebbe/Apoco/blob/master/WebContent/assets/img/presentation/Business.png
[social]: https://github.com/kalebbe/Apoco/blob/master/WebContent/assets/img/presentation/Social.png
[minesweeper]: https://github.com/kalebbe/Apoco/blob/master/WebContent/assets/img/presentation/Minesweeper.png

# Apoco
Come visit us at https://apoco-app.herokuapp.com/
![Apoco][logo]

# Video Demonstration
[![Demo](http://img.youtube.com/vi/YYS6Z7Q2Dq4/0.jpg)](http://www.youtube.com/watch?v=YYS6Z7Q2Dq4)
---
### Table of Contents
- [Directory](https://github.com/kalebbe/Apoco#directory)
- [Global](https://github.com/kalebbe/Apoco#global-loginregistrationlanding-page)
   * [Overview](https://github.com/kalebbe/Apoco#overview)
   * [Purpose](https://github.com/kalebbe/Apoco#purpose)
   * [Functionality](https://github.com/kalebbe/Apoco#functionality)
- [Social Platform](https://github.com/kalebbe/Apoco#social-platform)
   * [Overview](https://github.com/kalebbe/Apoco#overview)
   * [Purpose](https://github.com/kalebbe/Apoco#purpose)
   * [Functionality](https://github.com/kalebbe/Apoco#functionality)
- [Business Platform](https://github.com/kalebbe/Apoco#business-platform)
   * [Overview](https://github.com/kalebbe/Apoco#overview-2)
   * [Purpose](https://github.com/kalebbe/Apoco#purpose-2)
   * [Functionality](https://github.com/kalebbe/Apoco#functionality-2)
- [Dating Platform](https://github.com/kalebbe/Apoco#dating-platform)
### Directory
- [Models](https://github.com/kalebbe/Apoco/tree/master/src/com/gcu/model)- Classes containing object definitions.
- [Controllers](https://github.com/kalebbe/Apoco/tree/master/src/com/gcu/controller)- Classes controlling the flow of the application.
- [Data services](https://github.com/kalebbe/Apoco/tree/master/src/com/gcu/data)- Classes handling persistent data.
- [Business services](https://github.com/kalebbe/Apoco/tree/master/src/com/gcu/business)- Classes handling the business logic of the application.
- [Utilities](https://github.com/kalebbe/Apoco/tree/master/src/com/gcu/utilities)- Classes containing functions used throughout the application.
- [Views](https://github.com/kalebbe/Apoco/tree/master/WebContent/WEB-INF/pages)- Contains the visual pages of the website.
---
### Global (Login/Registration/Landing page)
![Global][global]
#### Overview
This section will give a brief explanation of the global module of the Apoco web application. This module includes the login, registration, index, user home, and account editor pages.
#### Purpose
The global module is the first interaction all users will have with the Apoco website. We tried our best to make a design and logo that would be visual appealing to all users and attract new users. All users that want to use any of the services Apoco offers are required to create a global account before they can make any other account. This gives our users the freedom to create profiles for the services they want to use rather than forcing them to use all of our services.
#### Functionality
The core functionality included in the Global module of Apoco include account registration, login, and account editting. Users must follow validation rules regarding usernames, emails, and passwords when creating or updating their account. Passwords are hashed using the BCrypt Maven dependency.

**Registration with BCrypt password hash**
```java
@Override
public int register(User t) {
   String hashPass = BCrypt.hashpw(t.getPassword(), BCrypt.gensalt()); // Takes the plain text pass and encrypts it
   t.setPassword(hashPass); // Updating the model object for database insertion
   if (dao.create(t)) { // creates a new
      return dao.getId(t.getEmail()); // Returns the ID of the newly created user for session capture
   } else
      return 0; // 0 tells me that the user creation has failed
}
```

**Hashed password check for login**
```java
@Override
public User checkPass(String pass, int id) {
   User user = dao.findById(id); // Grabs User object with ID

   if (BCrypt.checkpw(pass, user.getPassword())) { // Checks user's pass with database password for match
      return user;
   } else
      return null;
}
```
---
### Social Platform
![Social][social]
##### Overview
This section will give a brief explanation of the purpose of our social platform and a list of files used in the creation of the social platform.
##### Purpose
##### Functionality
**Minesweeper**
![Minesweeper][minesweeper]

---
### Business Platform
![Business][business]
##### Overview
##### Purpose
##### Functionality

---
### Dating Platform
![Dating][dating]
##### Overview
##### Purpose
##### Functionality
