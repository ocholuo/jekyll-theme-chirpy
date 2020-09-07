
# Building a Better Contact Sheet


From the image you can see there are two parameters which are being varied for each sub-image. 
- First, the rows are changed by color channel, where the top is the red channel, the middle is the green channel, and the bottom is the blue channel. Wait, why don't the colors look more red, green, and blue, in that order? Because the change you to be making is the ratio, or intensity, or that channel, in relationship to the other channels. 
- use three different intensities, 0.1 (reduce the channel a lot), 0.5 (reduce the channel in half), and 0.9 (reduce the channel only a little bit).

For instance, a pixel represented as (200, 100, 50) is a sort of burnt orange color. 
- So the top row of changes would create three alternative pixels, varying the first channel (red). one at (20, 100, 50), one at (100, 100, 50), and one at (180, 100, 50). 
- The next row would vary the second channel (blue), and would create pixels of color values (200, 10, 50), (200, 50, 50) and (200, 90, 50).

Note: A font is included for your usage if you would like! It's located in the file readonly/fanwood-webfont.ttf

Need some hints? Use them sparingly, see how much you can get done on your own first! The sample code given in the class has been cleaned up below, you might want to start from that.


```py
import PIL
from PIL import Image
from PIL import ImageEnhance
from PIL import ImageFont, ImageDraw


# read image and convert to RGB
image=Image.open("readonly/msi_recruitment.gif")
image=image.convert('RGB')
print(image)
# <PIL.Image.Image image mode=RGB size=800x450 at 0x7FF5D57C2E48>


# build a list of 9 images which have different color
pics = []

for i in range(3):
    for num in (0.1, 0.5, 0.9):
        colors = image.split()
        # Split this image into individual bands. returns a tuple of individual image bands from an image:
        # splitting an "RGB" image creates three new images each containing a copy of one of the original bands (red, green, blue).
        print(colors)
        # (
        # <PIL.Image.Image image mode=L size=800x450 at 0x7FF57101E3C8>, 
        # <PIL.Image.Image image mode=L size=800x450 at 0x7FF57101E390>, 
        # <PIL.Image.Image image mode=L size=800x450 at 0x7FF57101E550>
        # )

        # R G B
        mid = colors[i].point(lambda x:x*j)
        colors[i].paste(mid)
        # Maps this image through a lookup table or function.

        out = Image.merge(image.mode, colors)
        out = out.resize((int(out.width / 2), (int(out.height / 2))))

        rect = Image.new('RGB', (out.width, 30), color = (0, 0, 0))
        draw = ImageDraw.Draw(rect)
        fnt = ImageFont.truetype('readonly/fanwood-webfont.ttf', 20)
        draw.text((10, 10), 'channel 0 intensity {}'.format(i), font = fnt, fill = out.getpixel((0, 50)))

        sheet = PIL.Image.new(out.mode, (out.width, out.height + rect.height))
        sheet.paste(rect, (0,out.height))
        sheet.paste(out, (0,0))
        pics.append(sheet)


# create a contact sheet from different color
first_image=images[0]

contact_sheet=PIL.Image.new(first_image.mode, (first_image.width*3,first_image.height*3+3*85))
x=0
y=0

for image in pics:
    contact_sheet.paste(image, (x, y))
    if x + first_image.width == contact_sheet.width:
        x = 0
        y = y + first_image.height 
    else:
        x = x + first_image.width

display(contact_sheet)
```