
# Project - Nested Data Set (NestedPokemon)

[toc]

From the Python Basic offer by University of Michigan in Coursera: [17.8 Nested Data Set](https://fopp.umsi.education/books/published/fopp/NestedData/Exercises.html)

---

## Assignment 1

The nested dictionary, pokemon, shows the number of various Pokemon that each person has caught while playing Pokemon Go. Find the total number of rattatas, dittos, and pidgeys caught and assign to the variables r, d, and p respectively. Do not hardcode. Note: Be aware that not every trainer has caught a ditto.

---

## code

```py
pokemon = {'Trainer1':
          {'normal': {'rattatas':15, 'eevees': 2, 'ditto':1}, 'water': {'magikarps':3}, 'flying': {'zubats':8, 'pidgey': 12}},
          'Trainer2':
          {'normal': {'rattatas':25, 'eevees': 1}, 'water': {'magikarps':7}, 'flying': {'zubats':3, 'pidgey': 15}},
          'Trainer3':
          {'normal': {'rattatas':10, 'eevees': 3, 'ditto':2}, 'water': {'magikarps':2}, 'flying': {'zubats':3, 'pidgey': 20}},
          'Trainer4':
          {'normal': {'rattatas':17, 'eevees': 1}, 'water': {'magikarps':9}, 'flying': {'zubats':12, 'pidgey': 14}}}

print('\n----level 0 : pokemon is dic----')
print('----level 0 : trainers is key of pokemon dic----')
print('pokemon:', type(pokemon))   # dic
print("pokemon's keys", pokemon.keys())

r=0
d=0
p=0

for trainers in pokemon.keys():
    print('\n----level 1 : trainers is dic----')
    print("----level 1 : trainers is key of pokemon dic----")
    print('----level 1 : threestyles is key of trainers dic----')
    print('----level 2 : threestyles is dic----')
    print(trainers,':')
    print(pokemon[trainers])
    for threestyles in pokemon[trainers]:
        print('\n', threestyles, ':')
        print(pokemon[trainers][threestyles])
        print("----level 3 : monsters is key of threestyles dic----")
        for monsters in pokemon[trainers][threestyles]:
            print(monsters,pokemon[trainers][threestyles][monsters])
            if monsters=='rattatas':
                print('catchyou!!!!!!')
                r+=pokemon[trainers][threestyles][monsters]
            if monsters == 'ditto':
                print('catchyou!!!!!!')
                d+=pokemon[trainers][threestyles][monsters]
            if monsters == 'pidgey':
                print('catchyou!!!!!!')
                p+=pokemon[trainers][threestyles][monsters]

# result
----level 0 : pokemon is dic----
----level 0 : trainers is key of pokemon dic----
pokemon: <class 'dict'>
pokemon's keys ['Trainer1', 'Trainer2', 'Trainer3', 'Trainer4']

----level 1 : trainers is dic----
----level 1 : trainers is key of pokemon dic----
----level 1 : threestyles is key of trainers dic----
----level 2 : threestyles is dic----
Trainer1 :
{'normal': {'rattatas': 15, 'eevees': 2, 'ditto': 1}, 'water': {'magikarps': 3}, 'flying': {'zubats': 8, 'pidgey': 12}}

 normal :
{'rattatas': 15, 'eevees': 2, 'ditto': 1}
----level 3 : monsters is key of threestyles dic----
rattatas 15
catchyou!!!!!!
eevees 2
ditto 1
catchyou!!!!!!

 water :
{'magikarps': 3}
----level 3 : monsters is key of threestyles dic----
magikarps 3

 flying :
{'zubats': 8, 'pidgey': 12}
----level 3 : monsters is key of threestyles dic----
zubats 8
pidgey 12
catchyou!!!!!!

----level 1 : trainers is dic----
----level 1 : trainers is key of pokemon dic----
----level 1 : threestyles is key of trainers dic----
----level 2 : threestyles is dic----
Trainer2 :
{'normal': {'rattatas': 25, 'eevees': 1}, 'water': {'magikarps': 7}, 'flying': {'zubats': 3, 'pidgey': 15}}

 normal :
{'rattatas': 25, 'eevees': 1}
----level 3 : monsters is key of threestyles dic----
rattatas 25
catchyou!!!!!!
eevees 1

 water :
{'magikarps': 7}
----level 3 : monsters is key of threestyles dic----
magikarps 7

 flying :
{'zubats': 3, 'pidgey': 15}
----level 3 : monsters is key of threestyles dic----
zubats 3
pidgey 15
catchyou!!!!!!

----level 1 : trainers is dic----
----level 1 : trainers is key of pokemon dic----
----level 1 : threestyles is key of trainers dic----
----level 2 : threestyles is dic----
Trainer3 :
{'normal': {'rattatas': 10, 'eevees': 3, 'ditto': 2}, 'water': {'magikarps': 2}, 'flying': {'zubats': 3, 'pidgey': 20}}

 normal :
{'rattatas': 10, 'eevees': 3, 'ditto': 2}
----level 3 : monsters is key of threestyles dic----
rattatas 10
catchyou!!!!!!
eevees 3
ditto 2
catchyou!!!!!!

 water :
{'magikarps': 2}
----level 3 : monsters is key of threestyles dic----
magikarps 2

 flying :
{'zubats': 3, 'pidgey': 20}
----level 3 : monsters is key of threestyles dic----
zubats 3
pidgey 20
catchyou!!!!!!

----level 1 : trainers is dic----
----level 1 : trainers is key of pokemon dic----
----level 1 : threestyles is key of trainers dic----
----level 2 : threestyles is dic----
Trainer4 :
{'normal': {'rattatas': 17, 'eevees': 1}, 'water': {'magikarps': 9}, 'flying': {'zubats': 12, 'pidgey': 14}}

 normal :
{'rattatas': 17, 'eevees': 1}
----level 3 : monsters is key of threestyles dic----
rattatas 17
catchyou!!!!!!
eevees 1

 water :
{'magikarps': 9}
----level 3 : monsters is key of threestyles dic----
magikarps 9

 flying :
{'zubats': 12, 'pidgey': 14}
----level 3 : monsters is key of threestyles dic----
zubats 12
pidgey 14
catchyou!!!!!!
```

![Screen Shot 2020-03-25 at 22.10.38](https://i.imgur.com/mcbwga8.png)


---

## Assignment 2

Provided is a dictionary that contains pokemon go player data, where each player reveals the amount of candy each of their pokemon have. If you pooled all the data together, which pokemon has the highest number of candy? Assign that pokemon to the variable most_common_pokemon.

---

## code


```py
def sumtotal(lst):
    outlist={}
    for monster in lst:
        if monster[0] not in outlist:
            outlist[monster[0]]=0
        outlist[monster[0]]+=monster[1]
    return outlist

pokemon_go_data = {'bentspoon':
                  {'Rattata': 203, 'Pidgey': 120, 'Drowzee': 89, 'Squirtle': 35, 'Pikachu': 3, 'Eevee': 34, 'Magikarp': 300, 'Paras': 38},
                  'Laurne':
                  {'Pidgey': 169, 'Rattata': 245, 'Squirtle': 9, 'Caterpie': 38, 'Weedle': 97, 'Pikachu': 6, 'Nidoran': 44, 'Clefairy': 15, 'Zubat': 79, 'Dratini': 4},
                  'picklejarlid':
                  {'Rattata': 32, 'Drowzee': 15, 'Nidoran': 4, 'Bulbasaur': 3, 'Pidgey': 56, 'Weedle': 21, 'Oddish': 18, 'Magmar': 6, 'Spearow': 14},
                  'professoroak':
                  {'Charmander': 11, 'Ponyta': 9, 'Rattata': 107, 'Belsprout': 29, 'Seel': 19, 'Pidgey': 93, 'Shellder': 43, 'Drowzee': 245, 'Tauros': 18, 'Lapras': 18}}


# collect the dic to one list.
all=[]
for trainers in pokemon_go_data.keys():
    #print(type(pokemon_go_data[trainers]))
    #print(pokemon_go_data[trainers])
    for pokemons, total in pokemon_go_data[trainers].items():
        #print([pokemons, total])
        all.append([pokemons, total])

# collect one big dic from list.
sumdic=sumtotal(all)
#print(sumdic)

# find the most naughty pokemon.
most_common_pokemon=sorted(sumdic, reverse=True, key=lambda x:sumdic[x])[0]
print(most_common_pokemon)
```

![Screen Shot 2020-03-25 at 23.14.19](https://i.imgur.com/5kDDLdc.png)


.
