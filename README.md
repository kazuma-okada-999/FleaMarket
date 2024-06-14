# フリーマーケット

## 目次

1.  [はじめに](#introduction)
2.  [トピックの概要](#overview-of-topics)
3.  [環境設定](#environment)
4.  [アプリのテスト](#installing-dependencies)
5.  [参考資料](#resources)
6.  [コントリビューション](#contributing)

## イントロダクション

フリーマーケットアプリの実装をしています。
バックエンドはSpringBoot,フロントエンドはReactです。
フロントのソースコードは別のレポジトリです。
フロントをbuildしたものがSpringBootでsrc/main/resources/staticにあります。

## トピックの概要

imgBB というサイトの API を使用して画像をアップロード,画像の url の取得
を行っています

## 環境設定

github のソースコードを fork して自分の PC に clone してください

fleamarketというDBを作成してください。


## アプリのテスト

bootRun でローカルでアプリを起動できます。
マイグレーションも同時に実行されます。

localhost:8080 にアクセスして、アプリ画面が立ち上がるか試してください
何か商品を出品して出品したものが反映されるか試してください
