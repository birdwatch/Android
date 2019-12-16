# class
色々解説するにはまず、classについてから話始めなければいけません。

## classとは
class自体についてはなんか説明するよりも慣れてください（）

## 静的メンバー
### 静的フィールド
フィールドとはクラス自体が持っている情報（変数）のことです。簡単に言えば、クラス内にあってメソッドの外に書いてある変数のことです。下のclassをインスタンス化した場合、インスタンスごとに異なるデータが保存されます。

```java:main.java(一部)
public class Person(){
    int HP;
    String  sex;
    ...
}
```

仮にこのPersonのインスタンス全てのHPに同じ値を入れたい場合には、staticを使うことで共通の値を保持させることができます。

```java:main.java(一部)
    static int HP;
```

このHPにアクセスする場合、インスタンス化した名前を使わなくてもアクセスすることができます（上の場合Person.HP）。ていうかこの方法でアクセスするのが習わしというかそんな感じです。

Javaでは、Math.PIがあります。

### 静的メソッド
メソッドもstaticをつけることで静的にすることができます。

```java:main.java(一部)
public class Person(){
    
    static int getUnko(){
        return unko;
    }
    ...
}
```

静的フィールドと同様にインスタンス化した名前を使わなくても使用することができます。（上の場合Person.getUnko()）。

例えばJavaでは、Math.log()があります。

## アクセス制御
他のクラスからアクセスを制御したい時にはアクセス修飾子を使います。フィールドとメソッドにつけられる修飾子は以下の4つです。

[![Image from Gyazo](https://i.gyazo.com/cb4b8f0ffc5cee9f2802b9870179eac3.jpg)](https://gyazo.com/cb4b8f0ffc5cee9f2802b9870179eac3)

また、classにつけられるアクセス修飾子は以下の2つです。

[![Image from Gyazo](https://i.gyazo.com/918ff938a3bada1a6b0b1cad3ebe7cb8.jpg)](https://gyazo.com/918ff938a3bada1a6b0b1cad3ebe7cb8)

publicにしていないclassは他のclassからは存在そのものが隠されます。インスタンス化することもできませんし、静的メンバーを利用することもできません。

[![Image from Gyazo](https://i.gyazo.com/33f6d3019be3a240c7a7b6d39b1e2274.jpg)](https://gyazo.com/33f6d3019be3a240c7a7b6d39b1e2274)


```java:main.java(一部)
public class Person(){
    private int HP;
    private String sex;
    private String name;
    
    private Person(){

    }

    public void showName(){
        System.out.println(name);
    }

    // getter
    public String getName(){
        return name;
    }

    // setter
    public void setName(String name){
        this.name = name;
    }
    ...
}
```

基本的には
- フィールドは全てprivate
- getter/setterはすべてpublic
- 通常のメソッドは基本public

という風にします（まあ好みです）。

## 継承
実はAndroidのコードを書いてる間はこれをしていました。では見ていきましょう。

### 継承とは
似たような機能を持つclassを作るときに別々のclassとして実装しようとすると、全く同じメソッドやフィールドが存在していて書くのがだるくなってきます。そこで共通するフィールドやメソッドを記述して新しいclassを作ることができます。

例えば、AndroidではアプリのActivityを記述するひな形のclassとしてAppCombatActivityというものを使っています。実際に見てみましょう。

```java
// 1
public class MainActivity extends AppCompatActivity {

    // 2
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // 3
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
    }

    // 4


}
```

ここで行っていることを順に説明します。

1. classの継承元を**スーパークラス**、継承先を**サブクラス**といいます。サブクラスは複数存在できますが、スーパークラスは複数しか存在できません。サブクラスを作る際にはクラス名の横に**extends スーパークラス名**という風に記述します。
   
   上の場合だと、MainActivityというclassの中ではAppCombatActivityクラスのフィールド、メソッドを使用することができるようになります。

2. スーパークラスで定義したメソッドの内容を変更したいときにはメソッドの前に@overrideを付けます。これを**あのテーション**といいます。ただし、作成したメソッドの引数はスーパークラスと同じ順番、数にしなければいけないことに注意してください。
   
   また、overrideしたメソッドはなくなるわけではありません。

3. スーパークラスのメソッド、フィールドを使用したいときには**super.**をつけることで使用することができます。

    super.メソッド名();  
    super.フィールド名;
   
   上の場合だと、スーパークラスの同名のメソッドを使用した後に自分のしたい処理を記述していますね。

   ただし、**コンストラクタは継承されないことに注意してください**。

4. サブクラスのインスタンスにはスーパークラスのインスタンスが内包されています。簡単に言えば、サブクラスで記述しなくてもスーパークラスのメソッドやフィールドは勝手に動いています。
   
   例えば、戻るボタンを押したときの挙動を記述するonBackPressedというメソッドはMainActivityで書いていなくても押したときに反応します。

   ss
   

[![Image from Gyazo](https://i.gyazo.com/76448f3f90c63f5f163eb45fc6c41725.jpg)](https://gyazo.com/76448f3f90c63f5f163eb45fc6c41725)