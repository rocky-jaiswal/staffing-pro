// import Image from 'next/image'

function NavBar() {
  return (
    <div className="w-full border-t bg-green-200 lg:w-32 lg:border-r">
      <div className="flex justify-between lg:flex-col lg:justify-start">
        <div className="flex flex-col items-center justify-center border-b-2 border-cyan-700 p-4">
          {/* <Image src="/list.svg" alt="list" width="50px" height="50px" /> */}
          <p>Projects</p>
        </div>
        <div className="flex flex-col items-center justify-center border-b-2 border-cyan-700 p-4">
          {/* <Image src="/star.svg" alt="list" width="50px" height="50px" /> */}
          <p>Starred</p>
        </div>
        <div className="flex flex-col items-center justify-center border-b-2 border-cyan-700 p-4">
          {/* <Image src="/bell.svg" alt="list" width="50px" height="50px" /> */}
          <p>Updates</p>
        </div>
      </div>
    </div>
  )
}

export default NavBar
