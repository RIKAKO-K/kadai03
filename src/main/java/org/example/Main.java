package org.example;

import java.time.LocalDate;
import java.util.*;

public class Main {
    public enum eEntryKbn {
        //列挙子の定義
        INSERT,
        UPDATE,
        DELETE
    }
    public static void main(String[] args) {
        eEntryKbn EntryKbn;             //登録区分

        try{
            // サッカー日本代表選手の背番号、選手名をセット
            Map<Integer,String> samuraiBlue = new HashMap<>();
            samuraiBlue.put(6,"遠藤　航");
            samuraiBlue.put(14,"伊東　純也");
            samuraiBlue.put(18,"浅野　拓磨");
            samuraiBlue.put(8,"南野　拓実");
            samuraiBlue.put(11,"古橋　亨梧");
            samuraiBlue.put(5,"守田　英正");
            samuraiBlue.put(7,"前田　大然");  //怪我
            samuraiBlue.put(25,"三笘　薫");   //体調不良
            samuraiBlue.put(24,"旗手　怜央");
            samuraiBlue.put(15,"伊藤　敦樹");
            samuraiBlue.put(9,"上田　綺世");
            samuraiBlue.put(17,"田中　碧");
            samuraiBlue.put(13,"中村　敬斗");
            samuraiBlue.put(20,"久保　建英");
            //メンバー一覧
            System.out.println("----------SAMURAI BLUE 選手一覧(MF/FW)---------");
            samuraiBlue.forEach((key, value) -> System.out.println(key + " : " + value));

            //選手変更（上書き）
            System.out.println();
            System.out.println("------選手変更（10/9)------");
            System.out.println("背番号７：前田 → 川辺");
            samuraiBlue.put(7,"川辺　駿");
            System.out.println("背番号２５：三苫 → 奥抜");
            samuraiBlue.put(25,"奥抜　侃志");

            //選手登録・変更
            System.out.println();
            Scanner scanner = new Scanner(System.in);
            System.out.println("-------------選手の更新を行います（新規/変更/削除）-------------");
            System.out.println("【新規登録の場合：1　変更の場合：2　削除の場合：3】を入力してください");
            String EntryCd = scanner.next();
            switch (EntryCd){
                case "1":
                    //新規登録
                    System.out.println();
                    System.out.println("------選手追加("+ LocalDate.now() + ")------");
                    EntryKbn = eEntryKbn.INSERT;
                    break;
                case "2":
                    //変更
                    System.out.println();
                    System.out.println("------選手変更("+ LocalDate.now() + ")------");
                    EntryKbn = eEntryKbn.UPDATE;
                    break;
                case "3":
                    //削除
                    System.out.println();
                    System.out.println("------選手削除("+ LocalDate.now() + ")------");
                    EntryKbn = eEntryKbn.DELETE;
                    break;
                default:
                    //例外発生処理へ
                    throw new InputMismatchException("1～3以外の値が入力されています。");
            }

            //▼背番号入力
            System.out.println();
            System.out.println("選手の背番号を入力してください");
            String number = scanner.next();

            //処理前チェック
            if(samuraiBlue.containsKey(number)){ //★ここで入力した背番号(number)がmapのキーに含んでいるか確認したい
                //重複あり
                if(EntryKbn == eEntryKbn.INSERT) {
                    //新規（重複あり：False)
                    System.out.println("その背番号は既に使用されています。別の番号を指定してください。");
                }
            }else{
                //重複なし
                if(EntryKbn == eEntryKbn.UPDATE || EntryKbn == eEntryKbn.DELETE) {
                    //変更・削除（重複なし：False）
                    System.out.println(("該当選手が見つかりません。正しい背番号を再入力してください。"));
                }
            }

            //▼選手名入力
            System.out.println();
            System.out.println("名前を入力してください");
            String name = scanner.next();

            //処理実行
            if(EntryKbn == eEntryKbn.DELETE){
                //削除（remove)
                samuraiBlue.remove(Integer.valueOf(number));
            }else{
                //変更・新規（上書き）
                samuraiBlue.put(Integer.valueOf(number),name);
            }

            //最終メンバー一覧
            System.out.println();
            System.out.println("----------SAMURAI BLUE 選手一覧(MF/FW)---------");
            samuraiBlue.forEach((key, value) -> System.out.println(key + " : " + value));

            //▼次のワールドカップメンバー継続意思入力
            System.out.println();
            System.out.println("次のワールドカップもこのメンバーでいきますか？【YES：1　NO：2】");
            String continueKbn = scanner.next();
            if(Objects.equals(continueKbn, "2")){
                System.out.println("チーム解散");
                samuraiBlue.clear();
            }

        }catch (InputMismatchException e) {
            //例外発生
            System.out.println("[警告] 入力エラーが発生しました。" + e.getMessage());
        }
    }
}