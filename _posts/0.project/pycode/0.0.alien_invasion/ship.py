import pygame

class Ship:
# The __init__() method of Ship takes two parameters: the self reference and a reference to the current instance of the AlienInvasion class. 

# This will give Ship access to all the game resources defined in AlienInvasion. assign the screen to an attribute of Ship, so we can access it easily in all the methods in this class. 
# we access the screen’s rect attribute using the get_rect() method and assign it to self.screen_rect. Doing so allows us to place the ship in the correct location on the screen.


    def __init__(self, ai_game):
        # the self reference
        # ai_game: a reference to the current instance of AlienInvasion class
        """Initialize the ship and set its starting position.""" 
        self.screen = ai_game.screen
        self.screen_rect = ai_game.screen.get_rect()

        # Load the ship image and get its rect. 

        # To load the image
        # This function returns a surface representing the ship, which we assign to self.image. When the image is loaded, we call get_rect() to access the ship surface’s rect attribute so we can later use it to place the ship.
        self.image = pygame.image.load('image/ship.bmp') 
        self.rect = self.image.get_rect()

        # Start each new ship at the bottom center of the screen.
        self.rect.midbottom = self.screen_rect.midbottom


    def blitme(self):
        """Draw the ship at its current location.""" 
        self.screen.blit(self.image, self.rect)


