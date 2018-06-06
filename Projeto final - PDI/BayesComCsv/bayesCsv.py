import cv2
import numpy as np
import pandas as pd

dataframe = pd.read_csv('dataframe.csv')
train_data = dataframe.as_matrix(columns=dataframe.columns[:6]).astype(np.float32)
train_labels = dataframe.as_matrix(columns=dataframe.columns[6:]).astype(np.int32)

bayes = cv2.ml.NormalBayesClassifier_create()
bayes.train(train_data, cv2.ml.ROW_SAMPLE, train_labels)

img = cv2.imread('images//mapa.jpg')
hsv = cv2.cvtColor(img, cv2.COLOR_BGR2HSV)
blur3 = cv2.GaussianBlur(hsv, (3,3), 0)
blur5 = cv2.GaussianBlur(hsv, (5,5), 0)
blur7 = cv2.GaussianBlur(hsv, (7,7), 0)
height, width, channels = hsv.shape
img2 = np.zeros_like(img)

for j in range(height):
    for i in range(width):
        pixel = np.array([np.append(hsv[j, i], [blur3[j, i, 0], blur5[j, i, 0], blur7[j, i, 0]])]).astype(np.float32)
        retval, results = bayes.predict(pixel)
        if(results == 1):
            img2[j,i]=[0,0,255]
        #print('{}%\n'.format(int(j)/int(height)*100))

blur = cv2.medianBlur(img2,5)
added_image= cv2.addWeighted(img, 0.8, blur, 0.5, 0.2)
cv2.imwrite('result.png', added_image)
cv2.waitKey(0)
#cv2.destroyAllWindows()
