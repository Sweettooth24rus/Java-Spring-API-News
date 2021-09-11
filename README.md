# TestSpring
 Тестовое задание по Java Spring
 
К API обращаться по адресу "http://localhost:8080"

GET news показывает список всех новостей

GET news/"id" показывает новость с конкретным "id"

GET news/type/"id" показывает новости с типом "id"

POST news добавляет новость

PUT news/"id" изменяет данные о новости с "id"

DELETE news/"id" удаляет новость с "id"


GET newsType показывает список всех типов

GET newsType/"id" показывает тип с конкретным "id"

POST newsType добавляет тип

PUT newsType/"id" изменяет данные о типе с "id"

DELETE newsType/"id" удаляет тип с "id"


Тип новости имеет формат в JSON:

{"id", "name", "color"}


Новость имеет формат в JSON:

{"id", "name", "descriptionShort", "descriptionFull", "newsType"}


Просто в качестве примера параметр цвета типа новости сделан в виде Integer значения. По факту можно использовать Color, RGBA и т.д..
