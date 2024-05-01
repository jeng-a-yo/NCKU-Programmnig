# -*- coding: utf-8 -*-
"""計算機概論20240327 hw4.ipynb

Automatically generated by Colaboratory.

Original file is located at
    https://colab.research.google.com/drive/1-NXr0OJ_IKmOzIzLmYA1eX2qxxJWf5mO

#### 變數

```python
# 以下有很多不同的變數，你可以試試看 print(type(變數名稱)) 了解他們是什麼變數
a = 1
b = [1,2,3]
c = (1,2,3)
d = {1,2,3}
e = {'a':1, 'b':2, 'c':3}
f = 'a'
g = 'abcde'
h = {'apple': "蘋果", 'banana':'香蕉', 'cat': 'my favorite'}
I = '我'
k = 2+3j
```
"""

a = 1                   # 整數
b = [1,2,3]                # 列表
c = (1,2,3)                # 元組
d = {1,2,3}                # 集合
e = {'a':1, 'b':2, 'c':3}  # 字典
f = 'a'                  # 字串
g = 'abcde'                # 字串
h = {'apple': "蘋果", 'banana':'香蕉', 'cat': 'my favorite'}  # 字典
I = '我'                  # 字串
j = 3                   # 整數
k = 2+1j/2                 # 複數

print(type(k))              # 印出 k 的資料型態
print(k*(3+4j))             # 印出 k 乘以 (3+4j) 的結果
print(h['apple'])            # 印出字典 h 中 'apple' 對應的值

M = [[1,2,3],[4,5,6],[7,8,9,'x'],'x']  # 嵌套列表

print(type(M))        # 印出 M 的資料型態
print(M)              # 印出整個列表 M
print(M[0])           # 印出 M 的第一個元素
print(M[1])           # 印出 M 的第二個元素
print(M[3])           # 印出 M 的第四個元素
print(M[0][2])        # 印出 M 的第一個元素中的第三個元素

M[0][2] = 'a'         # 修改 M 的第一個元素中的第三個元素
print(M)              # 印出修改後的列表 M

L = (1,2,3)        # 元組 L 包含元素 1、2、3

print(L[1])       # 印出元組 L 的第二個元素

L[1] = 5          # 嘗試修改元組 L 的第二個元素 (會產生錯誤，因為元組是不可變的)

# 注意：嘗試修改元組的元素會導致錯誤，因為元組是不可變的資料結構

N = {3.14159,1,2,3,1,4,7,-1,5.6}   # 集合 N 包含多個元素，其中有重複的元素 1，但集合會自動去除重複的元素

print(N)                           # 印出集合 N

for x in N:                        # 逐一遍歷集合 N 中的元素
    print(x)                       # 印出每個元素

P = '1'             # 字串 P 包含數字 '1'

print(int(P)-int('5'))    # 將 P 與 '5' 轉換為整數，並進行減法運算，印出結果
print(str(1)+str(5))      # 將數字 1 和數字 5 轉換為字串，然後進行串接，印出結果

"""#### 使用範例：

```python
for element in iterable:
    # 做一些事情

```
請透過上面的範例，改
"""

a = 1                      # 整數
b = [1,2,3]                # 列表
c = (1,2,3)                # 元組
d = {1,2,3}                # 集合
e = {'a':1, 'b':2, 'c':3}        # 字典
k = 2+3j                   # 複數

"""以下就是星期五要交的工作
1. 寫一個變數，型別是字典，就是說明你在這一週學到的python關鍵字
2. 寫一個程式，可以體驗出( ), [ ] 以及 {} 這三種不同變數的差別
3. 寫一個程式，找出int, float, str的極限在哪裡？
4. 請把上面課程有介紹的部分，加上你自已的註解。
5. 請把這個檔案，透過另存新檔，或是分享連結的方式，繳交到moodle中。

以下是第一題的答案
"""

import sys

# Python 關鍵字的字典
pythonKeywords = {
    "print()": "用於將內容輸出到終端",
    "type()": "用於獲取物件的類型",
    "int()": "將值轉換為整數類型",
    "float()": "將值轉換為浮點數類型",
    "str()": "將物件轉換為字串類型",
    "tuple": "一種不可變序列",
    "list": "一種可變序列",
    "set": "一種無序且唯一的集合",
    "dictionary": "一種鍵-值對的資料結構",
}

print(f"pythonKeywords: {pythonKeywords}")

"""以下是第二題的答案"""

# 定義不同類型的變數
myTuple = (0, 1, 1, 2)
myList = [0, 1, 1, 2]
mySet = {0, 1, 1, 2, 3}
myDict = {0: 0, 1: 1, 2: 2, 3: 3}

# 嘗試修改 tuple 的值，捕獲 TypeError 並輸出錯誤訊息
try:
    myTuple[0] = 4
except TypeError as e:
    print("錯誤：", e)

# 嘗試取得 set 的索引，捕獲 TypeError 並輸出錯誤訊息
try:
    value = mySet[0]
    print("mySet 中數字 0 的索引:", value)
except TypeError as e:
    print("錯誤：", e)

# 輸出不同變數的值
print(f"myTuple: {myTuple}")
print(f"myList: {myList}")
print(f"mySet: {mySet}")
print(f"myDict: {myDict}")

"""以下是第三題的答案"""

# 輸出整數、浮點數和字串的極限值
print("python3 中 Int 沒有極限:")

floatLimit = sys.float_info.max
print("Float 的極限:", floatLimit)

strLimit = sys.maxsize
print("Str 的極限:", strLimit)

"""第四題的答案註解在上方"""