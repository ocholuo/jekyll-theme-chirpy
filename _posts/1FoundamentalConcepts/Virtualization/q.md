


首先KVM `Kernel-based Virtual Machine（KVM）`是Linux的一个内核驱动模块，它能够让Linux主机成为一个Hypervisor（虚拟机监控器）。在支持VMX（Virtual Machine Extension）功能的x86处理器中，Linux在原有的用户模式和内核模式中新增加了客户模式，并且客户模式也拥有自己的内核模式和用户模式，虚拟机就是运行在客户模式中。KVM模块的职责就是打开并初始化VMX功能，提供相应的接口以支持虚拟机的运行。


QEMU（quick emulator)本身并不包含或依赖KVM模块，而是一套由Fabrice Bellard编写的模拟计算机的自由软件。QEMU虚拟机是一个纯软件的实现，可以在没有KVM模块的情况下独立运行，但是性能比较低。QEMU有整套的虚拟机实现，包括处理器虚拟化、内存虚拟化以及I/O设备的虚拟化。QEMU是一个用户空间的进程，需要通过特定的接口才能调用到KVM模块提供的功能。从QEMU角度来看，虚拟机运行期间，QEMU通过KVM模块提供的系统调用接口进行内核设置，由KVM模块负责将虚拟机置于处理器的特殊模式运行。QEMU使用了KVM模块的虚拟化功能，为自己的虚拟机提供硬件虚拟化加速以提高虚拟机的性能。


KVM只模拟CPU和内存，因此一个客户机操作系统可以在宿主机上跑起来，但是你看不到它，无法和它沟通。于是，有人修改了QEMU代码，把他模拟CPU、内存的代码换成KVM，而网卡、显示器等留着，因此QEMU+KVM就成了一个完整的虚拟化平台。


KVM只是内核模块，用户并没法直接跟内核模块交互，需要借助用户空间的管理工具，而这个工具就是QEMU。KVM和QEMU相辅相成，QEMU通过KVM达到了硬件虚拟化的速度，而KVM则通过QEMU来模拟设备。对于KVM来说，其匹配的用户空间工具并不仅仅只有QEMU，还有其他的，比如RedHat开发的libvirt、virsh、virt-manager等，QEMU并不是KVM的唯一选择。


QEMU是个计算机模拟器，而KVM为计算机的模拟提供加速功能。



![Screen Shot 2020-11-25 at 10.53.38](https://i.imgur.com/DZAbrU7.png)




https://www.linkedin.com/learning/linux-foundation-cert-prep-virtualization-ubuntu/modify-a-qemu-kvm-virtual-machine




.



C:\Windows\system32\cmd.exe /c "powershell [System.Reflection.Assembly]::LoadWithPartialName(‘System.IO.Compression.FileSystem’); [System.IO.Compression.ZipFile]::ExtractToDirectory(‘C:/ProgramData/Amazon/CodeDeploy/9030ffdf-a835-4fd4-ad31-b746c35ec326/d-VRYRX7G17/bundle.tar’, ‘C:\ProgramData/Amazon/CodeDeploy/9030ffdf-a835-4fd4-ad31-b746c35ec326/d-VRYRX7G17/deployment-archive’) 2>&1"

"C:\Program Files (x86)\Microsoft\Edge\Application\msedge.exe" --type=utility --utility-sub-type=network.mojom.NetworkService --field-trial-handle=1888,11500288426666325061,3531686687339024611,131072 --lang=en-US --service-sandbox-type=network --mojo-platform-channel-handle=2324 /prefetch:8

"C:\Program Files (x86)\Red Gate\SQL Backup 10\(LOCAL)\SQBHostedStorageClient.exe" upload --instance (LOCAL) --s3bitbucket "sqlbkp-lvlprod" --sqb "S:\MSSQL\BACKUP\RedGate\FULL_(local)_production_20201125_020229.sqb"