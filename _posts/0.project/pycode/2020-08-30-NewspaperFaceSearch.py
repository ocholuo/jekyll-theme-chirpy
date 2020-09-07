
# The Assignment
# Take a ZIP file of images and process them
# The files in the ZIP file we provide are newspaper images. 
# write python code to search through the images looking for the occurrences of keywords and faces. 
# E.g. if you search for "pizza" it will return a contact sheet of all of the faces which were located on the newspaper page which mentions "pizza". 
# This will test your ability to learn a new (library), 
# use OpenCV to detect faces,
# use tesseract to do optical character recognition,
# use PIL to composite images together into contact sheets.

# Each page of the newspapers is saved as a single PNG image in a file called images.zip. These newspapers are in english, and contain a variety of stories, advertisements and images. Note: This file is fairly large (~200 MB) and may take some time to work with, I would encourage you to use small_img.zip for testing.

# Here's an example of the output expected. Using the small_img.zip file, if I search for the string "Christopher" I should see the following image:



import zipfile
import math
from PIL import Image
import pytesseract
import cv2 as cv
import numpy as np

# loading the face detection classifier
face_cascade = cv.CascadeClassifier('readonly/haarcascade_frontalface_default.xml')



#define a parsed source to work on:
imags = {}

#iterate through the zip file and save all the binarized versions to imags
def inputfile(zfile): 
    with zipfile.ZipFile(zfile, 'r') as files:
        for entry in files.infolist():
            with files.open(entry) as file:
                img = Image.open(file).convert('RGB')
                # display(img)
                imags[entry.filename] = {'pil_img':img}
    return imags



#parse all images text
def textfromimg(imags):
    for img_name in imags.keys():
        #  print(img_name)
        text = pytesseract.image_to_string( imags[img_name]['pil_img'] )
        # print(text)
        imags[img_name]['text'] = text
    return imags


#find the bounding boxes for all the faces from every page and extract them
def facefromimags(image):
    for img_name in imags.keys():
        cv_img = np.array(imags[img_name]['pil_img']) 
        gray_img = cv.cvtColor(cv_img, cv.COLOR_BGR2GRAY)
        
        faces_bounding_boxes = face_cascade.detectMultiScale(gray_img, 1.3, 5)
        
        imags[img_name]['faces'] = []
        
        for x,y,w,h in faces_bounding_boxes:
            face = imags[img_name]['pil_img'].crop((x,y,x+w,y+h))
            imags[img_name]['faces'].append(face)
    return imags
        

#create thumbnails
def thumfromimags(imags):
    for img_name in imags.keys():
        for face in imags[img_name]['faces']:
            face.thumbnail((100,100), Image.ANTIALIAS)
    return imags


#search the keyword in every page's text and return the faces
def search(keyword, ziplocation):

    zfile = ziplocation
    imags = inputfile(zfile)
    imags = textfromimg(imags)
    imags = facefromimags(imags)
    imags = thumfromimags(imags)

    for img_name in imags:
        if (keyword in imags[img_name]['text']):
            if(len(imags[img_name]['faces']) != 0):
                print("Result found in file {}".format(img_name))
                
                h = math.ceil(len(imags[img_name]['faces'])/5)
                
                result_sheet=Image.new('RGB',(500, 100*h))
                
                xc = 0
                yc = 0
                
                for img in imags[img_name]['faces']:
                    result_sheet.paste(img, (xc, yc))
                    if xc + 100 == result_sheet.width:
                        xc = 0
                        yc += 100
                    else:
                        xc += 100
                display(result_sheet)
                
            else:
                print("Result found in file {} \nBut there were no faces in that file\n\n".format(img_name))
    return

        
search('Chris', 'readonly/small_img.zip')

search('Mark', 'readonly/images.zip')

search('pizza', 'readonly/small_img.zip')
