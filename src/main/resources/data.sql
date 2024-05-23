INSERT INTO
    PRODUCT(product_id, created_at, created_by, modified_at, modified_by, category_code, product_name, price, product_img, product_info_img)
VALUES (1,'2024-05-11', '유정', '2024-05-11', '유정','신발', '스케쳐스고워크', 98500, 'https://img.shoppingntmall.com/goods/927/13144927_h.jpg', 'https://image.musinsa.com/images/prd_img/2022070611574200000034067.jpg');
INSERT INTO PRODUCT (product_id, created_at, created_by, modified_at, modified_by, category_code, product_name, price, product_img)
VALUES (2,'2024-05-11', '유정', '2024-05-11', '유정','신발', '아디다스운동화', 58000, 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSKJsIUk_LcSShMKWRAhbABHjIB_5x0vi_iLZs_PeTnSw&s');
INSERT INTO PRODUCT (product_id, created_at, created_by, modified_at, modified_by, category_code, product_name, price, product_img)
VALUES (3,'2024-05-11', '정호', '2024-05-11', '유정','남성의류', '가디건', 23000, 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSJEplzg10_SVhmRTqEY3dPRNvab_WNcvB-jj_iUJ6Yi8NfuJWO2NoduvpVGBnxm2_BeZAQlw');
INSERT INTO PRODUCT (product_id, created_at, created_by, modified_at, modified_by, category_code, product_name, price, product_img)
VALUES (4,'2024-05-11', '기니피그', '2024-05-11', '유정','신발', '7CM 구두', 2900, 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSJEplzg10_SVhmRTqEY3dPRNvab_WNcvB-jj_iUJ6Yi8NfuJWO2NoduvpVGBnxm2_BeZAQlw');
INSERT INTO PRODUCT (product_id, created_at, created_by, modified_at, modified_by, category_code, product_name, price, product_img)
VALUES (5,'2024-05-11', '유정', '2024-05-11', '유정','남성의류', '반팔티', 6300, 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSJEplzg10_SVhmRTqEY3dPRNvab_WNcvB-jj_iUJ6Yi8NfuJWO2NoduvpVGBnxm2_BeZAQlw');
INSERT INTO PRODUCT (product_id, created_at, created_by, modified_at, modified_by, category_code, product_name, price, product_img)
VALUES (6,'2024-05-01', '햄터', '2024-05-11', '유정','여성의류', '면바지', 59000, 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSJEplzg10_SVhmRTqEY3dPRNvab_WNcvB-jj_iUJ6Yi8NfuJWO2NoduvpVGBnxm2_BeZAQlw');
INSERT INTO PRODUCT (product_id, created_at, created_by, modified_at, modified_by, category_code, product_name, price, product_img)
VALUES (7,'2024-05-04', '나비', '2024-05-11', '유정','기타잡화', '양말세트5종', 27800, 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSJEplzg10_SVhmRTqEY3dPRNvab_WNcvB-jj_iUJ6Yi8NfuJWO2NoduvpVGBnxm2_BeZAQlw');
INSERT INTO PRODUCT (product_id, created_at, created_by, modified_at, modified_by, category_code, product_name, price, product_img)
VALUES (8,'2024-05-10', '정호', '2024-05-11', '유정','여성의류', '쿨텐다드 바지', 77000, 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSJEplzg10_SVhmRTqEY3dPRNvab_WNcvB-jj_iUJ6Yi8NfuJWO2NoduvpVGBnxm2_BeZAQlw');

INSERT INTO CART(cart_id, created_at, created_by, modified_at, modified_by, qty, product_id, id)
VALUES (1, '2024-05-10', '유정', '2024-05-11', '유정', 2, 1, 1);
INSERT INTO CART(cart_id, created_at, created_by, modified_at, modified_by, qty, product_id, id)
VALUES (2, '2024-05-10', '유정', '2024-05-11', '유정', 3, 8, 1, 1);

SELECT * FROM CART;
SELECT * FROM REVIEW;
select * from product;

INSERT INTO REVIEW(review_id, created_at, created_by, modified_at, modified_by, context, product_id, id)
VALUES (1, '2024-05-12', '햄쓰떡대마왕', '2024-05-12', '햄쓰떡대마왕', '스케쳐스 신발 넘넘 편하구 좋아요 강추!@!@!', 1, 1);
INSERT INTO REVIEW(review_id, created_at, created_by, modified_at, modified_by, context, product_id, id)
VALUES (2, '2024-05-12', '왕감자', '2024-05-13', '햄쓰떡대마왕', '스케쳐스 신발 괜찮네요. 발이 편해요', 1, id);