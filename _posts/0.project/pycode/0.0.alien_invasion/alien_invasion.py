import sys
import pygame
from settings import Settings

class AlienInvasion:
    """Overall class to manage game assets and behavior."""
    
    def __init__(self):
        """Initialize the game, and create game resources."""
        # pygame.init() function initializes the background settings that Pygame needs to work properly
        pygame.init()

        self.settings = Settings()
        # surface
        # The surface returned by display.set_mode() represents the entire game window.
        self.screen = pygame.display.set_mode((self.settings.screen_width, self.settings.screen_height))

        pygame.display.set_caption("Alien Invasion")

        # Set the background color. 
        self.bg_color = (230, 230, 230)

    
    def run_game(self):
        """Start the main loop for the game."""
        while True:
            # Watch for keyboard and mouse events.
            # pygame.event.get(): To access the events that Pygame detects.
            for event in pygame.event.get(): 
                # pygame.QUIT: click windows close button 
                if event.type == pygame.QUIT: 
                    sys.exit()

            # Redraw the screen during each pass through the loop. 
            self.screen.fill(self.bg_color)

            # Make the most recently drawn screen visible. 
            pygame.display.flip()




if __name__ == '__main__':
    ai = AlienInvasion()    # Make a game instance
    ai.run_game()           # run the game. 













    