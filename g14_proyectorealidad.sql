-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 21-10-2026 a las 01:08:26
-- Versión del servidor: 10.4.32-MariaDB
-- Versión de PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `g14_proyectorealidad`
--
CREATE DATABASE IF NOT EXISTS `g14_proyectorealidad` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `g14_proyectorealidad`;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `asiento`
--

CREATE TABLE `asiento` (
  `idLugar` int(11) NOT NULL,
  `idColectivo` int(11) NOT NULL,
  `numeroAsiento` int(11) NOT NULL,
  `estado` tinyint(4) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `colectivo`
--

CREATE TABLE `colectivo` (
  `idColectivo` int(11) NOT NULL,
  `matricula` varchar(10) NOT NULL,
  `marca` varchar(30) NOT NULL,
  `modelo` varchar(30) NOT NULL,
  `capacidad` int(11) NOT NULL,
  `chofer` varchar(50) NOT NULL,
  `estado` tinyint(4) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pasajero`
--

CREATE TABLE `pasajero` (
  `idPasajero` int(11) NOT NULL,
  `dni` int(11) NOT NULL,
  `nombre` varchar(30) NOT NULL,
  `apellido` varchar(30) NOT NULL,
  `correo` int(11) NOT NULL,
  `telefono` int(11) NOT NULL,
  `estado` tinyint(4) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pasajes`
--

CREATE TABLE `pasajes` (
  `idPasaje` int(11) NOT NULL,
  `idPasajero` int(11) NOT NULL,
  `idLugar` int(11) NOT NULL,
  `idRuta` int(11) NOT NULL,
  `fechaVenta` date NOT NULL,
  `horaVenta` time NOT NULL,
  `precio` decimal(10,0) NOT NULL,
  `estado` tinyint(4) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `rutaviaje`
--

CREATE TABLE `rutaviaje` (
  `idRuta` int(11) NOT NULL,
  `origen` varchar(50) NOT NULL,
  `destino` varchar(50) NOT NULL,
  `horaSalida` time NOT NULL,
  `horaLlegada` time NOT NULL,
  `idColectivo` int(11) NOT NULL,
  `precio` decimal(10,0) NOT NULL,
  `estado` tinyint(4) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `asiento`
--
ALTER TABLE `asiento`
  ADD PRIMARY KEY (`idLugar`),
  ADD KEY `idColectivo` (`idColectivo`);

--
-- Indices de la tabla `colectivo`
--
ALTER TABLE `colectivo`
  ADD PRIMARY KEY (`idColectivo`);

--
-- Indices de la tabla `pasajero`
--
ALTER TABLE `pasajero`
  ADD PRIMARY KEY (`idPasajero`);

--
-- Indices de la tabla `pasajes`
--
ALTER TABLE `pasajes`
  ADD PRIMARY KEY (`idPasaje`),
  ADD KEY `idPasajero` (`idPasajero`),
  ADD KEY `idLugar` (`idLugar`),
  ADD KEY `idRuta` (`idRuta`);

--
-- Indices de la tabla `rutaviaje`
--
ALTER TABLE `rutaviaje`
  ADD PRIMARY KEY (`idRuta`),
  ADD KEY `idColectivo` (`idColectivo`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `asiento`
--
ALTER TABLE `asiento`
  MODIFY `idLugar` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `colectivo`
--
ALTER TABLE `colectivo`
  MODIFY `idColectivo` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `pasajero`
--
ALTER TABLE `pasajero`
  MODIFY `idPasajero` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `pasajes`
--
ALTER TABLE `pasajes`
  MODIFY `idPasaje` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `rutaviaje`
--
ALTER TABLE `rutaviaje`
  MODIFY `idRuta` int(11) NOT NULL AUTO_INCREMENT;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `asiento`
--
ALTER TABLE `asiento`
  ADD CONSTRAINT `asiento_ibfk_1` FOREIGN KEY (`idColectivo`) REFERENCES `colectivo` (`idColectivo`);

--
-- Filtros para la tabla `pasajes`
--
ALTER TABLE `pasajes`
  ADD CONSTRAINT `pasajes_ibfk_1` FOREIGN KEY (`idPasajero`) REFERENCES `pasajero` (`idPasajero`),
  ADD CONSTRAINT `pasajes_ibfk_2` FOREIGN KEY (`idLugar`) REFERENCES `asiento` (`idLugar`),
  ADD CONSTRAINT `pasajes_ibfk_3` FOREIGN KEY (`idRuta`) REFERENCES `rutaviaje` (`idRuta`);

--
-- Filtros para la tabla `rutaviaje`
--
ALTER TABLE `rutaviaje`
  ADD CONSTRAINT `rutaviaje_ibfk_1` FOREIGN KEY (`idColectivo`) REFERENCES `colectivo` (`idColectivo`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
