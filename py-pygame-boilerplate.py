import pygame
from pygame import draw as d

tickDelay = 500
drawDelay = 100

c = [
    (255, 255, 255),
    (0, 0, 0)
]

mousePosition = (50, 50)
position = (50, 50)

def tick(screen):
    print(f'Tick')

def draw(screen):
    print('Draw')
    screen.fill((255, 255, 255))
    d.circle(screen, c[2], (0, 0), 50)

def main():
    pygame.init()

    window_size = (16*80, 9*80)
    screen = pygame.display.set_mode(window_size)
    pygame.display.set_caption("py-physics-2d")

    tick_timer = pygame.time.set_timer(pygame.USEREVENT, tickDelay)
    draw_timer = pygame.time.set_timer(pygame.USEREVENT+1, drawDelay)

    running = True
    while running:
        for event in pygame.event.get():
            if event.type == pygame.QUIT:
                running = False
            elif event.type == pygame.KEYDOWN:
                print(f"Pressed {event.key}")
            elif event.type == pygame.MOUSEBUTTONDOWN:
                print(f"Mouse clicked at {event.pos}")
            elif event.type == pygame.MOUSEMOTION:
                mousePosition = event.pos
                print(f"Mouse at {mousePosition}")
            elif event.type == pygame.USEREVENT:
                tick(screen)
            elif event.type == pygame.USEREVENT+1:
                draw(screen)

        pygame.display.update()

    pygame.quit()

if __name__ == '__main__':
    main()
