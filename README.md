1. one
   * nested
# Apoco
---
### Table of Contents
- [Directory](https://github.com/kalebbe/Apoco#directory)
- [Global](https://github.com/kalebbe/Apoco#global-loginregistrationlanding-page)
- [Social Platform](https://github.com/kalebbe/Apoco#social-platform)
   * [Overview](https://github.com/kalebbe/Apoco#overview)
   * [Purpose](https://github.com/kalebbe/Apoco#purpose)
   * [Functionality](https://github.com/kalebbe/Apoco#functionality)
   * [Social Views](https://github.com/kalebbe/Apoco#social-views)
   * [Social Controllers](https://github.com/kalebbe/Apoco#social-controllers)
   * [Social Models](https://github.com/kalebbe/Apoco#social-models)
   * [Social Business Services](https://github.com/kalebbe/Apoco#social-business-services)
   * [Social Data Services](https://github.com/kalebbe/Apoco#social-data-services)
- [Business Platform](https://github.com/kalebbe/Apoco#business-platform)
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
### Social Platform
##### Overview
This section will give a brief explanation of the purpose of our social platform and a list of files used in the creation of the social platform.
##### Purpose
##### Functionality
##### Social Views
- [socialProfile.jsp](https://github.com/kalebbe/Apoco/blob/master/WebContent/WEB-INF/pages/socialProfile.jsp)
- [socialDash.jsp](https://github.com/kalebbe/Apoco/blob/master/WebContent/WEB-INF/pages/socialDash.jsp)
- [socialFeed.jsp](https://github.com/kalebbe/Apoco/blob/master/WebContent/WEB-INF/pages/socialFeed.jsp)
- [viewProfile.jsp](https://github.com/kalebbe/Apoco/blob/master/WebContent/WEB-INF/pages/viewProfile.jsp)
- [playGames.jsp](https://github.com/kalebbe/Apoco/blob/master/WebContent/WEB-INF/pages/playGames.jsp)
- [minesweeper.jsp](https://github.com/kalebbe/Apoco/blob/master/WebContent/WEB-INF/pages/minesweeper.jsp)
- [friendList.jsp](https://github.com/kalebbe/Apoco/blob/master/WebContent/WEB-INF/pages/friendList.jsp)
##### Social Controllers
- [SocialController.java](https://github.com/kalebbe/Apoco/blob/master/src/com/gcu/controller/SocialController.java)
- [MineController.java](https://github.com/kalebbe/Apoco/blob/master/src/com/gcu/controller/MineController.java)
- [FriendController.java](https://github.com/kalebbe/Apoco/blob/master/src/com/gcu/controller/FriendController.java)
- [FeedController.java](https://github.com/kalebbe/Apoco/blob/master/src/com/gcu/controller/FeedController.java)
##### Social Models
- [Social.java](https://github.com/kalebbe/Apoco/blob/master/src/com/gcu/model/Social.java)
- [Message.java](https://github.com/kalebbe/Apoco/blob/master/src/com/gcu/model/Message.java)
- [Friend.java](https://github.com/kalebbe/Apoco/blob/master/src/com/gcu/model/Friend.java)
- [Feed.java](https://github.com/kalebbe/Apoco/blob/master/src/com/gcu/model/Feed.java)
- [Button.java](https://github.com/kalebbe/Apoco/blob/master/src/com/gcu/model/Button.java)
##### Social Business Services
- [SocialBusinessInterface.java](https://github.com/kalebbe/Apoco/blob/master/src/com/gcu/business/SocialBusinessInterface.java)
- [SocialBusinessService.java](https://github.com/kalebbe/Apoco/blob/master/src/com/gcu/business/SocialBusinessService.java)
- [MessageBusinessInterface.java](https://github.com/kalebbe/Apoco/blob/master/src/com/gcu/business/MessageBusinessInterface.java)
- [MessageBusinessService.java](https://github.com/kalebbe/Apoco/blob/master/src/com/gcu/business/MessageBusinessService.java)
- [FriendBusinessInterface.java](https://github.com/kalebbe/Apoco/blob/master/src/com/gcu/business/FriendBusinessInterface.java)
- [FriendBusinessService.java](https://github.com/kalebbe/Apoco/blob/master/src/com/gcu/business/FriendBusinessService.java)
- [FeedBusinessInterface.java](https://github.com/kalebbe/Apoco/blob/master/src/com/gcu/business/FeedBusinessInterface.java)
- [FeedBusinessService.java](https://github.com/kalebbe/Apoco/blob/master/src/com/gcu/business/FeedBusinessService.java)
##### Social Data Services
- [SocialDAO.java](https://github.com/kalebbe/Apoco/blob/master/src/com/gcu/data/SocialDAO.java)
- [SocialMapper.java](https://github.com/kalebbe/Apoco/blob/master/src/com/gcu/data/SocialMapper.java)
- [MessageDAO.java](https://github.com/kalebbe/Apoco/blob/master/src/com/gcu/data/MessageDAO.java)
- [MessageMapper.java](https://github.com/kalebbe/Apoco/blob/master/src/com/gcu/data/MessageMapper.java)
- [FriendDAO.java](https://github.com/kalebbe/Apoco/blob/master/src/com/gcu/data/FriendDAO.java)
- [FriendMapper.java](https://github.com/kalebbe/Apoco/blob/master/src/com/gcu/data/FriendMapper.java)
- [FeedDAO.java](https://github.com/kalebbe/Apoco/blob/master/src/com/gcu/data/FeedDAO.java)
- [FeedMapper.java](https://github.com/kalebbe/Apoco/blob/master/src/com/gcu/data/FeedMapper.java)
---
### Business Platform
---
### Dating Platform
