USE [master]
GO
/****** Object:  Database [orcunozdilDB]    Script Date: 23.12.2019 10:15:06 ******/
CREATE DATABASE [orcunozdilDB]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'orcunozdilDB', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL12.MSSQLSERVER\MSSQL\DATA\orcunozdilDB.mdf' , SIZE = 4288KB , MAXSIZE = UNLIMITED, FILEGROWTH = 1024KB )
 LOG ON 
( NAME = N'orcunozdilDB_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL12.MSSQLSERVER\MSSQL\DATA\orcunozdilDB_log.ldf' , SIZE = 9216KB , MAXSIZE = 2048GB , FILEGROWTH = 10%)
GO
ALTER DATABASE [orcunozdilDB] SET COMPATIBILITY_LEVEL = 120
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [orcunozdilDB].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [orcunozdilDB] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [orcunozdilDB] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [orcunozdilDB] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [orcunozdilDB] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [orcunozdilDB] SET ARITHABORT OFF 
GO
ALTER DATABASE [orcunozdilDB] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [orcunozdilDB] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [orcunozdilDB] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [orcunozdilDB] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [orcunozdilDB] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [orcunozdilDB] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [orcunozdilDB] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [orcunozdilDB] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [orcunozdilDB] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [orcunozdilDB] SET  ENABLE_BROKER 
GO
ALTER DATABASE [orcunozdilDB] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [orcunozdilDB] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [orcunozdilDB] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [orcunozdilDB] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [orcunozdilDB] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [orcunozdilDB] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [orcunozdilDB] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [orcunozdilDB] SET RECOVERY FULL 
GO
ALTER DATABASE [orcunozdilDB] SET  MULTI_USER 
GO
ALTER DATABASE [orcunozdilDB] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [orcunozdilDB] SET DB_CHAINING OFF 
GO
ALTER DATABASE [orcunozdilDB] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [orcunozdilDB] SET TARGET_RECOVERY_TIME = 0 SECONDS 
GO
ALTER DATABASE [orcunozdilDB] SET DELAYED_DURABILITY = DISABLED 
GO
USE [orcunozdilDB]
GO
/****** Object:  User [orcun]    Script Date: 23.12.2019 10:15:07 ******/
CREATE USER [orcun] FOR LOGIN [orcun] WITH DEFAULT_SCHEMA=[orcun]
GO
ALTER ROLE [db_ddladmin] ADD MEMBER [orcun]
GO
ALTER ROLE [db_backupoperator] ADD MEMBER [orcun]
GO
ALTER ROLE [db_datareader] ADD MEMBER [orcun]
GO
ALTER ROLE [db_datawriter] ADD MEMBER [orcun]
GO
/****** Object:  Schema [orcun]    Script Date: 23.12.2019 10:15:07 ******/
CREATE SCHEMA [orcun]
GO
/****** Object:  Table [orcun].[BsGameTable]    Script Date: 23.12.2019 10:15:07 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [orcun].[BsGameTable](
	[ID] [int] IDENTITY(1,1) NOT NULL,
	[HomePlayerID] [int] NULL,
	[VisitorPlayerID] [int] NULL,
	[HomeShips] [nvarchar](250) NULL,
	[VisitorShips] [nvarchar](250) NULL,
	[HomeMoves] [nvarchar](250) NULL,
	[VisitorMoves] [nvarchar](250) NULL,
	[GameResult] [int] NULL,
	[DateOfCreate] [datetime] NULL,
 CONSTRAINT [PK_BsGameTable] PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [orcun].[BsUsersTable]    Script Date: 23.12.2019 10:15:07 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [orcun].[BsUsersTable](
	[ID] [int] IDENTITY(1,1) NOT NULL,
	[Username] [nvarchar](250) NOT NULL,
	[Password] [nvarchar](250) NULL,
	[Nickname] [nvarchar](250) NULL,
	[Point] [int] NULL,
 CONSTRAINT [PK_BsUsersTable] PRIMARY KEY CLUSTERED 
(
	[Username] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [orcun].[BsWaitingTable]    Script Date: 23.12.2019 10:15:07 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [orcun].[BsWaitingTable](
	[ID] [int] IDENTITY(1,1) NOT NULL,
	[PlayerID] [int] NULL,
	[DateOfCreate] [datetime] NULL,
 CONSTRAINT [PK_BsWaitingTable] PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [orcun].[PredictionTable]    Script Date: 23.12.2019 10:15:08 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [orcun].[PredictionTable](
	[ID] [int] IDENTITY(1,1) NOT NULL,
	[CustomerId] [int] NULL,
	[CreditAmount] [float] NULL,
	[Age] [int] NULL,
	[OwnHouse] [int] NULL,
	[CreditCount] [int] NULL,
	[OwnPhone] [int] NULL,
	[Result] [int] NULL,
	[Isread] [int] NULL,
	[DateOfCreate] [datetime] NULL,
 CONSTRAINT [PK_PredictionTable] PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [orcun].[UserInfoTable]    Script Date: 23.12.2019 10:15:08 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [orcun].[UserInfoTable](
	[UserID] [int] IDENTITY(1,1) NOT NULL,
	[Address] [nvarchar](250) NULL,
	[GSM] [nvarchar](50) NULL,
	[Tel] [nvarchar](50) NULL,
	[Gender] [int] NULL,
 CONSTRAINT [PK_UserInfoTable] PRIMARY KEY CLUSTERED 
(
	[UserID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [orcun].[UserTable]    Script Date: 23.12.2019 10:15:08 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [orcun].[UserTable](
	[UserID] [int] IDENTITY(1,1) NOT NULL,
	[UserName] [nvarchar](250) NULL,
	[PassWord] [nvarchar](250) NULL,
	[Email] [nvarchar](250) NULL,
	[UserType] [int] NULL,
	[Name] [nvarchar](250) NULL,
	[LastName] [nvarchar](250) NULL,
	[AktivationStatus] [int] NULL,
 CONSTRAINT [PK_UserTable] PRIMARY KEY CLUSTERED 
(
	[UserID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  View [orcun].[GameDetailsView]    Script Date: 23.12.2019 10:15:08 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE VIEW [orcun].[GameDetailsView]
AS
SELECT        orcun.BsGameTable.ID, orcun.BsUsersTable.Nickname AS HostNick, orcun.BsGameTable.HomePlayerID, BsUsersTable_1.Nickname AS VisitorNick, orcun.BsGameTable.VisitorPlayerID, 
                         orcun.BsGameTable.HomeShips, orcun.BsGameTable.VisitorShips, orcun.BsGameTable.HomeMoves, orcun.BsGameTable.VisitorMoves, orcun.BsGameTable.GameResult
FROM            orcun.BsGameTable INNER JOIN
                         orcun.BsUsersTable ON orcun.BsGameTable.HomePlayerID = orcun.BsUsersTable.ID INNER JOIN
                         orcun.BsUsersTable AS BsUsersTable_1 ON orcun.BsGameTable.VisitorPlayerID = BsUsersTable_1.ID
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [IX_BsUsersTable]    Script Date: 23.12.2019 10:15:08 ******/
CREATE UNIQUE NONCLUSTERED INDEX [IX_BsUsersTable] ON [orcun].[BsUsersTable]
(
	[Nickname] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, DROP_EXISTING = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
GO
ALTER TABLE [orcun].[BsGameTable] ADD  CONSTRAINT [DF_BsGameTable_GameResult]  DEFAULT ((0)) FOR [GameResult]
GO
ALTER TABLE [orcun].[BsGameTable] ADD  CONSTRAINT [DF_BsGameTable_DateOfCreate]  DEFAULT (getdate()) FOR [DateOfCreate]
GO
ALTER TABLE [orcun].[BsUsersTable] ADD  CONSTRAINT [DF_BsUsersTable_Point]  DEFAULT ((0)) FOR [Point]
GO
ALTER TABLE [orcun].[BsWaitingTable] ADD  CONSTRAINT [DF_BsWaitingTable_DateOfCreate]  DEFAULT (getdate()) FOR [DateOfCreate]
GO
ALTER TABLE [orcun].[PredictionTable] ADD  CONSTRAINT [DF_PredictionTable_DateOfCreate]  DEFAULT (getdate()) FOR [DateOfCreate]
GO
/****** Object:  StoredProcedure [orcun].[GameRequest]    Script Date: 23.12.2019 10:15:08 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE PROCEDURE [orcun].[GameRequest] 
	@PlayerID int	
AS

declare @gameID int
declare @inGameBoard int
declare @inWaitingboard int
declare @someoneInWaitingboard int
declare @resultMessage nvarchar(50)
declare @home int

BEGIN
	SET @inGameBoard=(SELECT COUNT (*) FROM BsGameTable WHERE (HomePlayerID=@PlayerID OR VisitorPlayerID=@PlayerID) AND GameResult=0)
	IF @inGameBoard = 0
    BEGIN
       SET @gameID=0
	   SET @someoneInWaitingboard=(SELECT COUNT (*) FROM BsWaitingTable WHERE  NOT PlayerID=@PlayerID )
	   SET @inWaitingboard=(SELECT COUNT (*) FROM BsWaitingTable WHERE  PlayerID=@PlayerID )


	   IF @someoneInWaitingboard=0 AND @inWaitingboard=0
	   BEGIN
	   SET @resultMessage= '0,Added to waiting table'
	   INSERT INTO BsWaitingTable(PlayerID) VALUES(@PlayerID);
	   END

	   ELSE IF @someoneInWaitingboard=0 AND @inWaitingboard=1
	   BEGIN
	   SET @resultMessage= '0,Waiting for opponent'
	   END



	   ELSE
	   BEGIN
	   declare @opponentID int;
	   SET @opponentID=(SELECT TOP 1 PlayerID FROM BsWaitingTable ORDER BY DateOfCreate DESC );
	   DELETE FROM BsWaitingTable WHERE PlayerID=@opponentID;
	   INSERT INTO BsGameTable (HomePlayerID,VisitorPlayerID) VALUES(@PlayerID,@opponentID);
	   SET @gameID =(SELECT TOP 1 ID FROM BsGameTable WHERE HomePlayerID=@PlayerID OR VisitorPlayerID=@PlayerID ORDER BY DateOfCreate DESC);
	   SET @resultMessage= '1,Game Starts as Home'



	   END

    END

	ELSE
	BEGIN
		SET @gameID =(SELECT TOP 1 ID FROM BsGameTable WHERE HomePlayerID=@PlayerID OR VisitorPlayerID=@PlayerID ORDER BY DateOfCreate DESC);
		SET @home =(SELECT TOP 1 HomePlayerID FROM BsGameTable WHERE HomePlayerID=@PlayerID OR VisitorPlayerID=@PlayerID ORDER BY DateOfCreate DESC);
		
		IF @home=@PlayerID
		BEGIN
		SET @resultMessage= '1,Game Starts as Home'
		END
		ELSE
		BEGIN
		SET @resultMessage= '2,Game Starts as Visitor'
		END
		
		
	END
	
	
	
	SELECT @gameID,@resultMessage
END
GO
/****** Object:  StoredProcedure [orcun].[Login]    Script Date: 23.12.2019 10:15:08 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE PROCEDURE [orcun].[Login] 
	@username nvarchar(250),
	@password nvarchar(250)
AS
DECLARE @PlayerID int
BEGIN
	SET @PlayerID=0
	SET @PlayerID=(SELECT ID FROM BsUsersTable WHERE Username=@username AND Password=@password);

	IF @PlayerID>0
	BEGIN
	DELETE FROM BsWaitingTable WHERE PlayerID=@PlayerID;
	UPDATE BsGameTable SET GameResult=1 WHERE HomePlayerID=@PlayerID OR VisitorPlayerID=@PlayerID
	END

	SELECT * FROM BsUsersTable WHERE Username=@username AND Password=@password;
END
GO
EXEC sys.sp_addextendedproperty @name=N'MS_DiagramPane1', @value=N'[0E232FF0-B466-11cf-A24F-00AA00A3EFFF, 1.00]
Begin DesignProperties = 
   Begin PaneConfigurations = 
      Begin PaneConfiguration = 0
         NumPanes = 4
         Configuration = "(H (1[40] 4[20] 2[20] 3) )"
      End
      Begin PaneConfiguration = 1
         NumPanes = 3
         Configuration = "(H (1 [50] 4 [25] 3))"
      End
      Begin PaneConfiguration = 2
         NumPanes = 3
         Configuration = "(H (1 [50] 2 [25] 3))"
      End
      Begin PaneConfiguration = 3
         NumPanes = 3
         Configuration = "(H (4 [30] 2 [40] 3))"
      End
      Begin PaneConfiguration = 4
         NumPanes = 2
         Configuration = "(H (1 [56] 3))"
      End
      Begin PaneConfiguration = 5
         NumPanes = 2
         Configuration = "(H (2 [66] 3))"
      End
      Begin PaneConfiguration = 6
         NumPanes = 2
         Configuration = "(H (4 [50] 3))"
      End
      Begin PaneConfiguration = 7
         NumPanes = 1
         Configuration = "(V (3))"
      End
      Begin PaneConfiguration = 8
         NumPanes = 3
         Configuration = "(H (1[56] 4[18] 2) )"
      End
      Begin PaneConfiguration = 9
         NumPanes = 2
         Configuration = "(H (1 [75] 4))"
      End
      Begin PaneConfiguration = 10
         NumPanes = 2
         Configuration = "(H (1[66] 2) )"
      End
      Begin PaneConfiguration = 11
         NumPanes = 2
         Configuration = "(H (4 [60] 2))"
      End
      Begin PaneConfiguration = 12
         NumPanes = 1
         Configuration = "(H (1) )"
      End
      Begin PaneConfiguration = 13
         NumPanes = 1
         Configuration = "(V (4))"
      End
      Begin PaneConfiguration = 14
         NumPanes = 1
         Configuration = "(V (2))"
      End
      ActivePaneConfig = 0
   End
   Begin DiagramPane = 
      Begin Origin = 
         Top = 0
         Left = 0
      End
      Begin Tables = 
         Begin Table = "BsGameTable"
            Begin Extent = 
               Top = 6
               Left = 38
               Bottom = 205
               Right = 208
            End
            DisplayFlags = 280
            TopColumn = 1
         End
         Begin Table = "BsUsersTable"
            Begin Extent = 
               Top = 0
               Left = 457
               Bottom = 163
               Right = 627
            End
            DisplayFlags = 280
            TopColumn = 1
         End
         Begin Table = "BsUsersTable_1"
            Begin Extent = 
               Top = 120
               Left = 269
               Bottom = 250
               Right = 439
            End
            DisplayFlags = 280
            TopColumn = 1
         End
      End
   End
   Begin SQLPane = 
   End
   Begin DataPane = 
      Begin ParameterDefaults = ""
      End
      Begin ColumnWidths = 17
         Width = 284
         Width = 1500
         Width = 1500
         Width = 1500
         Width = 1500
         Width = 1500
         Width = 1500
         Width = 1500
         Width = 1500
         Width = 1500
         Width = 1500
         Width = 1500
         Width = 1500
         Width = 1500
         Width = 1500
         Width = 1500
         Width = 1500
      End
   End
   Begin CriteriaPane = 
      Begin ColumnWidths = 11
         Column = 1440
         Alias = 900
         Table = 1170
         Output = 720
         Append = 1400
         NewValue = 1170
         SortType = 1350
         SortOrder = 1410
         GroupBy = 1350
         Filter = 1350
         Or = 1350
         Or = 1350
         Or = 1350
      End
   End
End
' , @level0type=N'SCHEMA',@level0name=N'orcun', @level1type=N'VIEW',@level1name=N'GameDetailsView'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_DiagramPaneCount', @value=1 , @level0type=N'SCHEMA',@level0name=N'orcun', @level1type=N'VIEW',@level1name=N'GameDetailsView'
GO
USE [master]
GO
ALTER DATABASE [orcunozdilDB] SET  READ_WRITE 
GO
