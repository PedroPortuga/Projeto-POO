-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Tempo de geração: 21-Maio-2023 às 01:22
-- Versão do servidor: 10.4.25-MariaDB
-- versão do PHP: 7.4.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Banco de dados: `mvcdb`
--

-- --------------------------------------------------------

--
-- Estrutura da tabela `alunotb`
--

CREATE TABLE `alunotb` (
  `RGM_Aluno` int(8) NOT NULL,
  `Nome_Aluno` varchar(100) DEFAULT NULL,
  `Dat_Nas_Aluno` date DEFAULT NULL,
  `CPF_Aluno` bigint(11) DEFAULT NULL,
  `Email_Aluno` varchar(50) DEFAULT NULL,
  `End_Aluno` varchar(60) DEFAULT NULL,
  `Muni_Aluno` varchar(40) DEFAULT NULL,
  `UF_Aluno` varchar(2) DEFAULT NULL,
  `Cel_Aluno` bigint(11) DEFAULT NULL,
  `Cur_Aluno` varchar(50) DEFAULT NULL,
  `Cam_Aluno` varchar(30) DEFAULT NULL,
  `Per_Aluno` enum('Matutino','Vespertino','Noturno') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Extraindo dados da tabela `alunotb`
--

INSERT INTO `alunotb` (`RGM_Aluno`, `Nome_Aluno`, `Dat_Nas_Aluno`, `CPF_Aluno`, `Email_Aluno`, `End_Aluno`, `Muni_Aluno`, `UF_Aluno`, `Cel_Aluno`, `Cur_Aluno`, `Cam_Aluno`, `Per_Aluno`) VALUES
(12345678, 'Jorge da Silva', '2001-09-11', 22233344456, 'Jorgezin@bol.com.br', 'Rua Logi Ali, 35', 'Longe', 'SP', 11940028922, 'Engenharia da Computação', 'Pinheiros', 'Vespertino');

-- --------------------------------------------------------

--
-- Estrutura da tabela `notefaltb`
--

CREATE TABLE `notefaltb` (
  `RGM_NotFal` int(11) NOT NULL,
  `Dis_NotFal` varchar(50) DEFAULT NULL,
  `Sem_NotFal` varchar(6) DEFAULT NULL,
  `Not_NotFal` varchar(4) DEFAULT NULL,
  `Fal_NotFal` varchar(2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Extraindo dados da tabela `notefaltb`
--

INSERT INTO `notefaltb` (`RGM_NotFal`, `Dis_NotFal`, `Sem_NotFal`, `Not_NotFal`, `Fal_NotFal`) VALUES
(12345678, 'Programação Orientada a Objetos', '2023-3', '3,00', '1');

--
-- Índices para tabelas despejadas
--

--
-- Índices para tabela `alunotb`
--
ALTER TABLE `alunotb`
  ADD PRIMARY KEY (`RGM_Aluno`);

--
-- Índices para tabela `notefaltb`
--
ALTER TABLE `notefaltb`
  ADD PRIMARY KEY (`RGM_NotFal`) USING BTREE;

--
-- Restrições para despejos de tabelas
--

--
-- Limitadores para a tabela `notefaltb`
--
ALTER TABLE `notefaltb`
  ADD CONSTRAINT `notefaltb_ibfk_1` FOREIGN KEY (`RGM_NotFal`) REFERENCES `alunotb` (`RGM_Aluno`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
