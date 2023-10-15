-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 15-10-2023 a las 19:27:47
-- Versión del servidor: 10.4.28-MariaDB
-- Versión de PHP: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `ra2_grupo2`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `employees`
--

CREATE TABLE `employees` (
  `id` int(20) NOT NULL,
  `nif` varchar(20) NOT NULL,
  `name` varchar(100) NOT NULL,
  `surname` varchar(100) NOT NULL,
  `email` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `employees`
--

INSERT INTO `employees` (`id`, `nif`, `name`, `surname`, `email`, `password`) VALUES
(0, '77177451P', 'Juan', 'Fernandez', 'juan@gmail.com', '123456'),
(1, '77177450P', 'Juan Jesus', 'Fernandez', 'jj@gmail.com', '123456'),
(2, '11111111A', 'Admin', 'Admin', 'admin@gmail.com', '123456');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `products`
--

CREATE TABLE `products` (
  `id` int(20) NOT NULL,
  `quantity` int(20) NOT NULL,
  `price` float NOT NULL,
  `name` varchar(100) NOT NULL,
  `description` text NOT NULL,
  `category` varchar(20) NOT NULL,
  `image` varchar(100) NOT NULL,
  `deleted` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `products`
--

INSERT INTO `products` (`id`, `quantity`, `price`, `name`, `description`, `category`, `image`, `deleted`) VALUES
(0, 90, 2.5, 'Nueces', 'fruto con cáscara leñosa y dura,\nque al partirlo por la mitad presenta\nuna pulpa seca de color marrón\namarillento y con forma de cerebro', 'Frutos secos', 'src/main/resources/defaultImages/default.png', 0),
(1, 80, 1.5, 'Almendra', 'Fruto de cáscara un tanto dura y\nquebradiza de color marrón-beige,\ncuya semilla es la parte comestible.', 'Frutos secos', 'src/main/resources/images/Almendra_1231697063984566.jpg', 0),
(2, 45, 2.89, 'Cocacola', 'Bebida azucarada gaseosa vendida\na nivel mundial en tiendas,\nrestaurantes y máquinas\nexpendedoras en más de\ndoscientos países o territorios.', 'Refrescos', 'src/main/resources/images/Cocacola_1231697388965749.jpg', 0),
(3, 45, 1.34, 'Fanta', 'Bebida azucarada gaseosa\nvendida a nivel mundial en\ntiendas, restaurantes y máquinas\nexpendedoras en más de\ndoscientos países o territorios.', 'Refrescos', 'src/main/resources/images/Fanta_1231697389063953.jpg', 0),
(4, 67, 4.5, 'Pizza', 'Especie de torta de harina\namasada, encima de la cual se\npone queso, tomate frito y otros\ningredientes, y que se cuece en\nel horno.', 'Preparados', 'src/main/resources/images/Pizza_1231697389243832.jpg', 0),
(5, 67, 2.5, 'Tortilla', 'Alimento preparado con huevo\nbatido, cuajado con aceite en la\nsartén y de forma redonda o\nalargada, al que a veces se\nañaden otros ingredientes.', 'Preparados', 'src/main/resources/images/Tortilla_1231697389365546.jpg', 0),
(6, 180, 1.34, 'Pasta de dientes', 'Es una sustancia química que,\ncomo hemos dicho, es una de las\npartes indispensables de la higiene\ndental diaria.', 'Higiene', 'src/main/resources/images/Pasta_de_dientes_1231697389445226.jpg', 0),
(7, 180, 2.45, 'Cepillo de dientes', 'Es un instrumento de higiene oral\nque se utiliza para limpiar la boca a\ntravés del cepillado.', 'Higiene', 'src/main/resources/images/Cepillo_de_dientes_1231697389547792.jpg', 0);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `suppliers`
--

CREATE TABLE `suppliers` (
  `id` int(20) NOT NULL,
  `name` varchar(100) NOT NULL,
  `address` varchar(255) NOT NULL,
  `phone` varchar(20) NOT NULL,
  `deleted` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `suppliers`
--

INSERT INTO `suppliers` (`id`, `name`, `address`, `phone`, `deleted`) VALUES
(0, 'Coca Cola', 'Sede central en Atlanta (EEUU).', '900 199 202 ', 0),
(1, 'Grefusa', 'Av. Alcalde Cantos Ropero,\n1127H, 11408 Jerez de la Frontera,\nCádiz', '687 053 353', 0),
(2, 'Mercadona', 'C. Butrón, 31, 28022 Madrid', '800 500 220', 0),
(3, 'Comprador', 'Avenida de la salinera N22', '633 038 940', 0);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `transactions`
--

CREATE TABLE `transactions` (
  `id` int(20) NOT NULL,
  `product_id` int(20) NOT NULL,
  `supplier_id` int(20) NOT NULL,
  `quantity` int(20) NOT NULL,
  `date` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `transactions`
--

INSERT INTO `transactions` (`id`, `product_id`, `supplier_id`, `quantity`, `date`) VALUES
(1, 0, 1, 100, '2023-10-15 17:17:09'),
(2, 1, 1, 100, '2023-10-15 17:17:28'),
(3, 2, 0, 50, '2023-10-15 17:17:38'),
(4, 3, 0, 50, '2023-10-15 17:17:51'),
(5, 4, 2, 75, '2023-10-15 17:18:03'),
(6, 5, 2, 75, '2023-10-15 17:18:11'),
(7, 6, 2, 200, '2023-10-15 17:18:31'),
(8, 7, 2, 200, '2023-10-15 17:18:39'),
(9, 0, 3, -10, '2023-10-15 17:19:13'),
(10, 1, 3, -20, '2023-10-15 17:19:21'),
(11, 2, 3, -5, '2023-10-15 17:19:39'),
(12, 3, 3, -5, '2023-10-15 17:19:51'),
(13, 4, 3, -8, '2023-10-15 17:20:40'),
(14, 5, 3, -8, '2023-10-15 17:20:46'),
(15, 6, 3, -20, '2023-10-15 17:20:55'),
(16, 7, 3, -20, '2023-10-15 17:21:02');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `employees`
--
ALTER TABLE `employees`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `email` (`email`),
  ADD UNIQUE KEY `nif` (`nif`);

--
-- Indices de la tabla `products`
--
ALTER TABLE `products`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `suppliers`
--
ALTER TABLE `suppliers`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `transactions`
--
ALTER TABLE `transactions`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk-transactions_products_product_id` (`product_id`),
  ADD KEY `fk_transactions_suppliers_supplier_id` (`supplier_id`);

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `transactions`
--
ALTER TABLE `transactions`
  ADD CONSTRAINT `fk-transactions_products_product_id` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`),
  ADD CONSTRAINT `fk_transactions_suppliers_supplier_id` FOREIGN KEY (`supplier_id`) REFERENCES `suppliers` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
