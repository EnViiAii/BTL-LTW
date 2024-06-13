-- Adminer 4.8.1 MySQL 8.0.36 dump

SET NAMES utf8;
SET time_zone = '+00:00';
SET foreign_key_checks = 0;
SET sql_mode = 'NO_AUTO_VALUE_ON_ZERO';

SET NAMES utf8mb4;

DROP TABLE IF EXISTS `anh_san_pham`;
CREATE TABLE `anh_san_pham` (
                                `ma_anh` int NOT NULL AUTO_INCREMENT,
                                `ma_san_pham` int NOT NULL,
                                `duong_dan` varchar(256) DEFAULT NULL,
                                PRIMARY KEY (`ma_anh`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO `anh_san_pham` (`ma_anh`, `ma_san_pham`, `duong_dan`) VALUES
                                                                      (1,	1,	'/public/e1e9d7b8-6626-46fb-b8e9-91790d1ed4ef.jpg'),
                                                                      (2,	2,	'/public/8b3dfc6f-a2a2-4c22-ae4a-0c95fd61044a.jpg');

DROP TABLE IF EXISTS `binh_luan`;
CREATE TABLE `binh_luan` (
                             `ma_binh_luan` int NOT NULL AUTO_INCREMENT,
                             `noi_dung_binh_luan` varchar(256) DEFAULT NULL,
                             `ma_san_pham` int NOT NULL,
                             `ngay_binh_luan` timestamp NULL DEFAULT NULL,
                             `ma_nguoi_dung` int NOT NULL,
                             `ma_binh_luan_goc` int DEFAULT NULL,
                             PRIMARY KEY (`ma_binh_luan`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


DROP TABLE IF EXISTS `chat_lieu`;
CREATE TABLE `chat_lieu` (
                             `ma_chat_lieu` int NOT NULL AUTO_INCREMENT,
                             `ten_chat_lieu` varchar(64) DEFAULT NULL,
                             PRIMARY KEY (`ma_chat_lieu`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO `chat_lieu` (`ma_chat_lieu`, `ten_chat_lieu`) VALUES
                                                              (1,	'Vải'),
                                                              (2,	'Da'),
                                                              (3,	'Len'),
                                                              (4,	'Nhung'),
                                                              (5,	'Khác');

DROP TABLE IF EXISTS `danh_gia`;
CREATE TABLE `danh_gia` (
                            `ma_danh_gia` int NOT NULL AUTO_INCREMENT,
                            `ma_nguoi_danh_gia` int NOT NULL,
                            `noi_dung_danh_gia` varchar(256) DEFAULT NULL,
                            `diem_danh_gia` int DEFAULT NULL,
                            `ngay_danh_gia` timestamp NULL DEFAULT NULL,
                            `ma_san_pham_dat` int NOT NULL,
                            PRIMARY KEY (`ma_danh_gia`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO `danh_gia` (`ma_danh_gia`, `ma_nguoi_danh_gia`, `noi_dung_danh_gia`, `diem_danh_gia`, `ngay_danh_gia`, `ma_san_pham_dat`) VALUES
    (1,	1,	'',	4,	'2024-05-17 14:53:46',	1);

DROP TABLE IF EXISTS `dat_hang`;
CREATE TABLE `dat_hang` (
                            `ma_dat_hang` int NOT NULL AUTO_INCREMENT,
                            `dia_chi_giao` varchar(128) DEFAULT NULL,
                            `hinh_thuc_thanh_toan` int DEFAULT NULL,
                            `ghi_chu` varchar(128) DEFAULT NULL,
                            `phuong_thuc_van_chuyen` int DEFAULT NULL,
                            `sdt_nhan` varchar(16) DEFAULT NULL,
                            `ten_nguoi_nhan` varchar(64) DEFAULT NULL,
                            `trang_thai` int DEFAULT NULL,
                            `ngay_dat_hang` timestamp NULL DEFAULT NULL,
                            `tong_tien` double DEFAULT NULL,
                            `ma_nguoi_dat` int NOT NULL,
                            PRIMARY KEY (`ma_dat_hang`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO `dat_hang` (`ma_dat_hang`, `dia_chi_giao`, `hinh_thuc_thanh_toan`, `ghi_chu`, `phuong_thuc_van_chuyen`, `sdt_nhan`, `ten_nguoi_nhan`, `trang_thai`, `ngay_dat_hang`, `tong_tien`, `ma_nguoi_dat`) VALUES
    (2,	'Không có địa chỉ',	1,	NULL,	3,	'+84123456789',	'Đây là tét',	1,	'2024-05-17 14:53:27',	100000,	1);

DROP TABLE IF EXISTS `gio_hang`;
CREATE TABLE `gio_hang` (
                            `ma_muc_gio_hang` bigint NOT NULL AUTO_INCREMENT,
                            `ma_nguoi_dung` int NOT NULL,
                            `ma_san_pham` int NOT NULL,
                            `so_luong_mua` int NOT NULL,
                            PRIMARY KEY (`ma_muc_gio_hang`),
                            KEY `ma_nguoi_dung` (`ma_nguoi_dung`),
                            KEY `ma_san_pham` (`ma_san_pham`),
                            CONSTRAINT `gio_hang_ibfk_1` FOREIGN KEY (`ma_nguoi_dung`) REFERENCES `nguoi_dung` (`ma_nguoi_dung`),
                            CONSTRAINT `gio_hang_ibfk_2` FOREIGN KEY (`ma_san_pham`) REFERENCES `san_pham` (`ma_san_pham`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


DROP TABLE IF EXISTS `lich_su_hanh_dong`;
CREATE TABLE `lich_su_hanh_dong` (
                                     `ma_hang_dong` int NOT NULL AUTO_INCREMENT,
                                     `ma_nguoi_dung` int NOT NULL,
                                     `loai_hanh_dong` int DEFAULT NULL,
                                     `dia_chi_ip` varchar(256) DEFAULT NULL,
                                     `thoi_gian` timestamp NULL DEFAULT NULL,
                                     `chi_tiet` varchar(128) DEFAULT NULL,
                                     `thanh_cong` tinyint(1) DEFAULT NULL,
                                     PRIMARY KEY (`ma_hang_dong`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO `lich_su_hanh_dong` (`ma_hang_dong`, `ma_nguoi_dung`, `loai_hanh_dong`, `dia_chi_ip`, `thoi_gian`, `chi_tiet`, `thanh_cong`) VALUES
                                                                                                                                             (1,	1,	1,	'172.25.0.1',	'2024-05-17 13:39:41',	NULL,	1),
                                                                                                                                             (2,	1,	1,	'172.25.0.1',	'2024-05-17 13:46:04',	NULL,	1),
                                                                                                                                             (3,	1,	1,	'172.25.0.1',	'2024-05-17 14:42:01',	NULL,	1),
                                                                                                                                             (4,	1,	3,	'172.25.0.1',	'2024-05-17 14:52:28',	NULL,	1),
                                                                                                                                             (5,	1,	1,	'172.25.0.1',	'2024-05-17 14:52:40',	NULL,	1);

DROP TABLE IF EXISTS `nguoi_dung`;
CREATE TABLE `nguoi_dung` (
                              `ma_nguoi_dung` int NOT NULL AUTO_INCREMENT,
                              `ten_hien_thi` varchar(32) DEFAULT NULL,
                              `ten_dang_nhap` varchar(32) DEFAULT NULL,
                              `email` varchar(64) DEFAULT NULL,
                              `so_dien_thoai` varchar(16) DEFAULT NULL,
                              `mat_khau` varchar(256) DEFAULT NULL,
                              `da_khoa` tinyint(1) DEFAULT NULL,
                              `co_gang_dang_nhap` int DEFAULT NULL,
                              `thoi_gian_tao` timestamp NULL DEFAULT NULL,
                              PRIMARY KEY (`ma_nguoi_dung`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO `nguoi_dung` (`ma_nguoi_dung`, `ten_hien_thi`, `ten_dang_nhap`, `email`, `so_dien_thoai`, `mat_khau`, `da_khoa`, `co_gang_dang_nhap`, `thoi_gian_tao`) VALUES
    (1,	'Luu Van Dung',	'dunglv',	'dunglv202@gmail.com',	'0987654321',	'$2a$10$xnQ5yz8NYJTsRDEMsFUwRu.ZGspgb6rrAIKyQvzziR1xc8voSedDW',	0,	0,	'2002-01-01 00:00:00');

DROP TABLE IF EXISTS `phan_quyen`;
CREATE TABLE `phan_quyen` (
                              `ma_nguoi_dung` int NOT NULL,
                              `ma_quyen` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO `phan_quyen` (`ma_nguoi_dung`, `ma_quyen`) VALUES
                                                           (1,	2),
                                                           (1,	3),
                                                           (1,	4),
                                                           (1,	1);

DROP TABLE IF EXISTS `quyen`;
CREATE TABLE `quyen` (
                         `ma_quyen` int NOT NULL AUTO_INCREMENT,
                         `ten_quyen` varchar(32) DEFAULT NULL,
                         `mo_ta` varchar(256) DEFAULT NULL,
                         PRIMARY KEY (`ma_quyen`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO `quyen` (`ma_quyen`, `ten_quyen`, `mo_ta`) VALUES
                                                           (1,	'KHACH_HANG',	NULL),
                                                           (2,	'ADMIN',	NULL),
                                                           (3,	'QUAN_LY',	NULL),
                                                           (4,	'NHAN_VIEN',	NULL);

DROP TABLE IF EXISTS `san_pham`;
CREATE TABLE `san_pham` (
                            `ma_san_pham` int NOT NULL AUTO_INCREMENT,
                            `ten_san_pham` varchar(128) DEFAULT NULL,
                            `anh_xem_truoc` varchar(256) DEFAULT '/public/anh-trong.jpg',
                            `diem_trung_binh` double DEFAULT NULL,
                            `so_danh_gia` int DEFAULT NULL,
                            `mo_ta` text,
                            `gia` double DEFAULT NULL,
                            `so_luong` int DEFAULT NULL,
                            `trong_luong` double DEFAULT NULL,
                            `kich_thuoc` varchar(16) DEFAULT NULL,
                            `ma_chat_lieu` int NOT NULL,
                            `ma_thuong_hieu` int NOT NULL,
                            `ma_the_loai` int NOT NULL,
                            `da_an` tinyint(1) DEFAULT NULL,
                            PRIMARY KEY (`ma_san_pham`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO `san_pham` (`ma_san_pham`, `ten_san_pham`, `anh_xem_truoc`, `diem_trung_binh`, `so_danh_gia`, `mo_ta`, `gia`, `so_luong`, `trong_luong`, `kich_thuoc`, `ma_chat_lieu`, `ma_thuong_hieu`, `ma_the_loai`, `da_an`) VALUES
                                                                                                                                                                                                                                 (1,	'Giày sneaker trắng',	'/public/e1e9d7b8-6626-46fb-b8e9-91790d1ed4ef.jpg',	NULL,	NULL,	'',	100000,	9,	NULL,	'38',	1,	2,	3,	0),
                                                                                                                                                                                                                                 (2,	'Giày sneaker xanh lá',	'/public/8b3dfc6f-a2a2-4c22-ae4a-0c95fd61044a.jpg',	NULL,	NULL,	'',	150000,	19,	NULL,	'39',	1,	1,	3,	0);

DROP TABLE IF EXISTS `san_pham_dat`;
CREATE TABLE `san_pham_dat` (
                                `ma_san_pham_dat` int NOT NULL AUTO_INCREMENT,
                                `ma_san_pham` int NOT NULL,
                                `don_gia` double DEFAULT NULL,
                                `so_luong` int DEFAULT NULL,
                                `ma_dat_hang` int DEFAULT NULL,
                                PRIMARY KEY (`ma_san_pham_dat`),
                                KEY `ma_dat_hang` (`ma_dat_hang`),
                                CONSTRAINT `san_pham_dat_ibfk_1` FOREIGN KEY (`ma_dat_hang`) REFERENCES `dat_hang` (`ma_dat_hang`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO `san_pham_dat` (`ma_san_pham_dat`, `ma_san_pham`, `don_gia`, `so_luong`, `ma_dat_hang`) VALUES
    (1,	1,	100000,	1,	2);

DROP TABLE IF EXISTS `the_loai`;
CREATE TABLE `the_loai` (
                            `ma_the_loai` int NOT NULL AUTO_INCREMENT,
                            `ten_the_loai` varchar(64) DEFAULT NULL,
                            `anh_dai_dien` varchar(256) DEFAULT '/public/anh-trong.jpg',
                            PRIMARY KEY (`ma_the_loai`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO `the_loai` (`ma_the_loai`, `ten_the_loai`, `anh_dai_dien`) VALUES
                                                                           (1,	'Quần',	'/public/anh-trong.jpg'),
                                                                           (2,	'Áo',	'/public/anh-trong.jpg'),
                                                                           (3,	'Giày',	'/public/anh-trong.jpg'),
                                                                           (4,	'Váy',	'/public/anh-trong.jpg'),
                                                                           (5,	'Mũ',	'/public/anh-trong.jpg'),
                                                                           (6,	'Túi xách',	'/public/anh-trong.jpg');

DROP TABLE IF EXISTS `thuong_hieu`;
CREATE TABLE `thuong_hieu` (
                               `ma_thuong_hieu` int NOT NULL AUTO_INCREMENT,
                               `ten_thuong_hieu` varchar(64) DEFAULT NULL,
                               PRIMARY KEY (`ma_thuong_hieu`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO `thuong_hieu` (`ma_thuong_hieu`, `ten_thuong_hieu`) VALUES
                                                                    (1,	'Adidas'),
                                                                    (2,	'Nike'),
                                                                    (3,	'Puma'),
                                                                    (4,	'5S'),
                                                                    (5,	'Dior'),
                                                                    (6,	'Louis Vuitton'),
                                                                    (7,	'Gucci'),
                                                                    (8,	'Hermes');

DROP TABLE IF EXISTS `tin_tuc`;
CREATE TABLE `tin_tuc` (
                           `ma_tin_tuc` int NOT NULL AUTO_INCREMENT,
                           `tieu_de` varchar(128) DEFAULT NULL,
                           `mo_ta` varchar(256) DEFAULT NULL,
                           `nhan_nut_lien_ket` varchar(256) DEFAULT NULL,
                           `anh` varchar(256) DEFAULT NULL,
                           PRIMARY KEY (`ma_tin_tuc`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- 2024-05-17 14:54:41