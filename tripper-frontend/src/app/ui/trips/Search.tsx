"use client";

import { useSearchParams, usePathname, useRouter } from "next/navigation";
import { useDebouncedCallback } from "use-debounce";
// import { MagnifyingGlassIcon } from '@heroicons/react/24/outline';

export default function Search({ placeholder }: { placeholder: string }) {
  const searchParams = useSearchParams();

  // use Next.js's useRouter and usePathname hooks to update the URL.
  const pathname = usePathname();
  const { replace } = useRouter();

  /*
    This function will wrap the contents of handleSearch,
    and only run the code after a specific time once the user has stopped typing (300ms).
        -> prevents updating the URL on every keystroke
  */
  const handleSearch = useDebouncedCallback((term) => {
    /*
        URLSearchParams is a Web API that provides utility methods for manipulating the URL query parameters. 
        Instead of creating a complex string literal, we can use it to get the params string like ?page=1&query=a
    */
    const params = new URLSearchParams(searchParams);

    if (term) {
      params.set("q", term.trim());
    } else {
      params.delete("q");
    }

    replace(`${pathname}?${params.toString()}`);
  }, 300);

  return (
    <div className="relative flex flex-1 flex-shrink-0">
      <label htmlFor="search" className="sr-only">
        Search
      </label>

      {/* defaultValue vs. value / Controlled vs. Uncontrolled */}
      <input
        className="peer block w-full rounded-md border border-gray-200 py-[9px] pl-10 text-sm outline-2 placeholder:text-gray-500"
        placeholder={placeholder}
        defaultValue={searchParams.get("query")?.toString()}
        onChange={(e) => {
          handleSearch(e.target.value);
        }}
      />

      {/* <MagnifyingGlassIcon className="absolute left-3 top-1/2 h-[18px] w-[18px] -translate-y-1/2 text-gray-500 peer-focus:text-gray-900" /> */}
    </div>
  );
}
