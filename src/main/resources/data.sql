INSERT INTO
    PRODUCT(product_id, created_at, created_by, modified_at, modified_by, category_code, product_name, price, product_img)
VALUES (1,'2024-05-11', '유정', '2024-05-11', '유정','신발', '스케쳐스고워크', 98500, 'https://img.shoppingntmall.com/goods/927/13144927_h.jpg');
INSERT INTO PRODUCT (product_id, created_at, created_by, modified_at, modified_by, category_code, product_name, price, product_img)
VALUES (2,'2024-05-11', '유정', '2024-05-11', '유정','신발', '아디다스운동화', 58000, 'https://www.google.com/url?sa=i&url=https%3A%2F%2Fm.ssg.com%2Fitem%2FitemView.ssg%3FitemId%3D1000523234684&psig=AOvVaw1xdjl9o2e3xqbYKF_2HLnG&ust=1715504820620000&source=images&cd=vfe&opi=89978449&ved=0CBIQjRxqFwoTCKjCus2fhYYDFQAAAAAdAAAAABAF');
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

INSERT INTO CART(cart_id, created_at, created_by, modified_at, modified_by, qty, product_id)
VALUES (1, '2024-05-10', '유정', '2024-05-11', '유정', 2, 1);
INSERT INTO CART(cart_id, created_at, created_by, modified_at, modified_by, qty, product_id)
VALUES (2, '2024-05-10', '유정', '2024-05-11', '유정', 3, 8);

SELECT * FROM CART;
SELECT * FROM REVIEW;
select * from product;

INSERT INTO REVIEW(review_id, created_at, created_by, modified_at, modified_by, context, product_id)
VALUES (1, '2024-05-12', '햄쓰떡대마왕', '2024-05-12', '햄쓰떡대마왕', '스케쳐스 신발 넘넘 편하구 좋아요 강추!@!@!', 1);