
from PIL import Image
import numpy as np 
import colorsys

im = Image.open("googleEarthTop.jpg")
aux = 0
################################################área verde###########################################################
def identifyGA(im, aux):	
	img_result = np.ones((im.width, im.height, 3), 'uint8')	
	for i in range(img_result.shape[1]):
		for j in range(img_result.shape[0]):
			Red,Green,Blue = im.getpixel((j,i))
			Red/=255
			Green/=255
			Blue/=255
            
			h,s,v = colorsys.rgb_to_hsv(Red, Green, Blue)           
			if ((h)*360 >= 60 and (h)*360 <= 135 and s >= 0.18  and v >= 0.05 and v <= 0.8):
				img_result[j,i,0] = 125
				img_result[j,i,1] = 0
				img_result[j,i,2] = 0	
				aux+=1
			else:	
				img_result[j,i,0] = Red*255
				img_result[j,i,1] = Green*255
				img_result[j,i,2] = Blue*255

    	#####################################informações que vao ser tiradas da imagem final###############################
	percentualAreaVerde = (aux*100)/im.width*im.height
	print('Percentua de área verde nessa imagem: %f %%' % ((percentualAreaVerde)))

	metrosQuadradosAreaVerde = (aux*2944.1929)/(im.width*im.height)
	print('Metros quadrados de área verde nessa imagem: %f' % ((metrosQuadradosAreaVerde)))
	
	return img_result    


img_GArea = identifyGA(im, aux)
               
###############################################dados área verde###################################################

######################### info sobre um dos pixels do mar que estava sendo pintado no inicio######################
h,s,v = colorsys.rgb_to_hsv(126/255, 134/255, 113/255)
print("cor: %f   saturação:  %f     valor:  %f   " % ((h*360, s, v)))

###################################################################################################################
img_resultFinal = Image.fromarray(img_GArea).transpose(Image.TRANSPOSE)

img_resultFinal.show()
