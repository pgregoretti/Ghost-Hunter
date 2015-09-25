#Ghost Hunter
This was a group project worked on last semester. We had one month to make a "Ghost Hunter" game that met certain criteria, listed below.

While this was an advanced Java course, we did not spend any time in lecture going over how things should actually be made in Android Studio. It's Java, but it is very different!

One student who had already took the course gave a presentation on Android Studio, but was not allowed to give too much on how to write our project. It was helpful but also pretty short. After the presentation our group was under the impression that we should write everything with asynctask, but that became a problem later when we tried to have more than 5. It also slowed down the app.

Work was distributed pretty equally. I wrote RandomDropHandler and GhostCollisionHandler. I tried to make the object animate, but didn't have enough time to figure out how to implement it. I also made the "Ghost Hunter" high score background and main background by photoshopping Mario sprite art.

Overall we got a 100 on the project. It is a little buggy and not perfect, but it was fun to figure out how to make an Android application and design a game. I wanted to try and improve it this summer, but was never able to get around to it. Maybe one day.

#Criteria

**Required Functionality**

1. Have a splash screen noting at least
  *All teammate names
  *The university (University of Virginia), course (CS 2110), and semester (Spring 2015)
  *Some kind of identifying logo, image, or name
  *If someone gave you unusually major assistance, acknowledge them too
2. See the user/character on the screen (relative location to onscreen elements) and see the user/character move. Suggestions on implementing movement are as follows (this is by no means a complete list):
  *Buttons (e.g. up, down, left, right) to move the user/character
  *Use the android accelerometer (tilting to move the user/character)
  *Use touch (touching the screen or draging your finger across the screen to cause movement)
  *(Do NOT use the GPS feature with Google maps! Try to find alternative ways to implement movement)
3. Auto-generate ghosts at locations relatively near the user
  *note: this does not mean the ghosts then dynamically move after being spawned. If you want this functionality see the "move" optional item below)
4. Have a view of the world (e.g. a room, some scenery, dungeon, …) where the user and ghosts are both seen on screen relative to one another
  *note: this does not need to have a map background; user + ghost on a blank background is fine
5. Have some technique for "killing" ghosts
6. Keep track of some sort of progress statistics (points, number of kills, etc.) and be able to display that information


There was also a large table listing other functionality options and their points value (we had to add up to 100 or greater for a 100 grade). This included random ghost movement, ghosts hurting the player, special items, bombs that clear nearby ghosts, ranging difficulty, nice visuals, sound, and animation.
