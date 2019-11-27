[logo]: https://github.com/kalebbe/Apoco/blob/master/WebContent/assets/img/presentation/Apoco.png
[global]: https://github.com/kalebbe/Apoco/blob/master/WebContent/assets/img/presentation/Global.png
[dating]: https://github.com/kalebbe/Apoco/blob/master/WebContent/assets/img/presentation/Dating.png
[business]: https://github.com/kalebbe/Apoco/blob/master/WebContent/assets/img/presentation/Business.png
[social]: https://github.com/kalebbe/Apoco/blob/master/WebContent/assets/img/presentation/Social.png
[minesweeper]: https://github.com/kalebbe/Apoco/blob/master/WebContent/assets/img/presentation/Minesweeper.png
[sysdesign]: https://github.com/kalebbe/Apoco/blob/master/WebContent/assets/img/presentation/SystemDesign-Apoco.png

# Apoco
Come visit us at https://apoco-app.herokuapp.com/
![Apoco][logo]

# Video Demonstration
[![Demo](http://img.youtube.com/vi/YYS6Z7Q2Dq4/0.jpg)](http://www.youtube.com/watch?v=YYS6Z7Q2Dq4)

https://youtu.be/YYS6Z7Q2Dq4
---
### Table of Contents
- [Directory](https://github.com/kalebbe/Apoco#directory)
- [System Design](https://github.com/kalebbe/Apoco#top-down-system-design-model)
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
   * [Overview](https://github.com/kalebbe/Apoco#overview-3)
   * [Purpose](https://github.com/kalebbe/Apoco#purpose-3)
   * [Functionality](https://github.com/kalebbe/Apoco#functionality-3)
### Directory
- [Models](https://github.com/kalebbe/Apoco/tree/master/src/com/gcu/model)- Classes containing object definitions.
- [Controllers](https://github.com/kalebbe/Apoco/tree/master/src/com/gcu/controller)- Classes controlling the flow of the application.
- [Data services](https://github.com/kalebbe/Apoco/tree/master/src/com/gcu/data)- Classes handling persistent data.
- [Business services](https://github.com/kalebbe/Apoco/tree/master/src/com/gcu/business)- Classes handling the business logic of the application.
- [Utilities](https://github.com/kalebbe/Apoco/tree/master/src/com/gcu/utilities)- Classes containing functions used throughout the application.
- [Views](https://github.com/kalebbe/Apoco/tree/master/WebContent/WEB-INF/pages)- Contains the visual pages of the website.
---

## Top-down System Design Model
![System Design][sysdesign]

## Global (Login/Registration/Landing page)
![Global][global]
### Overview
This section will give a brief explanation of the global module of the Apoco web application. This module includes the login, registration, index, user home, and account editor pages.
### Purpose
The global module is the first interaction all users will have with the Apoco website. We tried our best to make a design and logo that would be visual appealing to all users and attract new users. All users that want to use any of the services Apoco offers are required to create a global account before they can make any other account. This gives our users the freedom to create profiles for the services they want to use rather than forcing them to use all of our services.
### Functionality
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
## Social Platform
![Social][social]
### Overview
This section will give a brief explanation of the purpose of our social platform as well as the functionality found within the social platform. Some code snippets will also be included and diagrams showing some of the functionality.
### Purpose
The social platform is the biggest part of Apoco and has been the focus of most of the project and testing. Our social media allows our users to connect with each other and communicate with their closest friends. Users can also enable privacy mode so that other users can only search for them by their email if they choose to do so. This is just a shell of what our social media platform is going to become over time. More browser games can be developed and added to the website as requested and we can give users a way to compete with their friends.
### Functionality
Current functionaly includes profile creation, minesweeper, post creation/deletion, voting on posts, adding friends, and messaging friends. Our social platform is still in the early stages like the rest of our website and can only grow from here.
**Creating a social profile (Controller code)**
```java
@RequestMapping(path = "/submitSocial", method = RequestMethod.POST)
  public ModelAndView submitSocial(@Valid @ModelAttribute("social") Social social, BindingResult result,
			HttpSession session) {
		if (result.hasErrors()) { //Binding result holds data validation errors from the model
			return new ModelAndView("socialProfile", "social", social); //Returns socialProfile with errors
		}
		social.setUserId((int)session.getAttribute("id"));
		if(ss.createSocial(social)) { //Successfully creates a social profile in the database
      //Session attribute that lets the user skip the profile creation in the future
			session.setAttribute("hasSocial", true); 
			return new ModelAndView("socialDash", "social", social);
		}
		else { //Social profile failed insertion into database.
			return new ModelAndView("socialProfile", "social", new Social());
```
**Minesweeper**
![Minesweeper][minesweeper]
**Minesweeper board generation**
```java
public void generateBoard(int size) {
  btnHolder = new Button[size][size];
		
	//first loops to check if each button will be a bomb and then set it
	for(int i = 0; i < size; i++) {
		for(int j = 0; j < size; j++) {
			btnHolder[i][j] = new Button(i, j);
			if(isActive()) {
				btnHolder[i][j].setLive(true);
			}
		}
	}
		
	//Second loops to set live neighbors for each button
	for(int i = 0; i < size; i++) {
		for(int j = 0; j < size; j++) {
			countNeighbors(i, j, size);
		}
	}
}
```
---
## Business Platform
![Business][business]
### Overview
### Purpose
### Functionality

---
## Dating Platform
![Dating][dating]
### Overview
### Purpose
### Functionality
